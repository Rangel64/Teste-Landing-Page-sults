package br.com.rangeltestesults.landingpagesults.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.rangeltestesults.landingpagesults.model.Conta;
import br.com.rangeltestesults.landingpagesults.model.ValidationResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/conta")
public class ContaController {

    public boolean validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }
        return nome.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñÇç\\s]+$");
    }

    public boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(regex);
    }

    public static boolean validarTelefone(String numero) {
        String regex = "^\\(\\d{2}\\) \\d{5}-\\d{4}$";
        return numero.matches(regex);
    }

    @PostMapping("/cadastrar")
    @ResponseBody
    public ValidationResult cadastrar(Conta conta) {
        ValidationResult result = new ValidationResult();
        if(!validarNome(conta.getNome())){
            if(conta.getNome()==""){
                result.setSuccess(false);
                result.setMessage("Informe seu none");
                result.setInvalidField("nome");
                return result;
            }
            else{
                result.setSuccess(false);
                result.setMessage("Nome inválido");
                result.setInvalidField("nome");
                return result;
            }
        }
        else{
            if(!validarNome(conta.getEmpresa())){
                if(conta.getEmpresa()==""){
                    result.setSuccess(false);
                    result.setMessage("Informe a empresa");
                    result.setInvalidField("empresa");
                    return result;
                }
                else{
                    result.setSuccess(false);
                    result.setMessage("Empresa inválida");
                    result.setInvalidField("empresa");
                    return result;
                }
            }
            else{
                if(!(validarEmail(conta.getEmail()))){
                    if(conta.getEmail()==""){
                        result.setSuccess(false);
                        result.setMessage("Informe o Email");
                        result.setInvalidField("email");
                        return result;   
                    }
                    else{
                        result.setSuccess(false);
                        result.setMessage("Email inválido");
                        result.setInvalidField("email");
                        return result;
                    }
                }
                else{
                    if(!validarTelefone(conta.getCelular())){
                        if(conta.getCelular()==""){
                            result.setSuccess(false);
                            result.setMessage("Informe o celular");
                            result.setInvalidField("celular");
                            return result;
                        }
                        else{
                            result.setSuccess(false);
                            result.setMessage("Celular inválido");
                            result.setInvalidField("celular");
                            return result;
                        }
                    }
                    else{
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            // Converter objeto para JSON
                            String json = mapper.writeValueAsString(conta);
                            System.out.println(json);
                            result.setSuccess(true);
                            result.setMessage("Cadastro realizado com sucesso");
                            result.setInvalidField(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                            result.setSuccess(false);
                            result.setMessage("Ocorreu um problema na criacao do json do cadastro!");
                            result.setInvalidField(null);
                        }
                    }
                }
            }
        }

        return result;    
    }
}
