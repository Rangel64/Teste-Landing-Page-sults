
function enviarDados(dados) {
    fetch('/conta/cadastrar', {
        method: 'POST',
        body: dados
        
    })
    .then(response => response.json())
    .then(data => {
        if (!data.success) {
            var divLoading = document.getElementById('loading')
            divLoading.style.display = 'none'
            
            console.error('Erro:', data.message);

            document.getElementById('form-warning-wrapper').style.display='block';

            if(data.invalidField=="nome"){
                document.getElementById('form-warning-message').innerText=data.message;
            }
            
            if(data.invalidField=="empresa"){
                document.getElementById('form-warning-message').innerText=data.message;
            }
            
            if(data.invalidField=="email"){
                document.getElementById('form-warning-message').innerText=data.message;
            }
            
            if(data.invalidField=="celular"){
                document.getElementById('form-warning-message').innerText=data.message;
            }
            
        } else {       
            
            var divLoading = document.getElementById('loading')
            var divSuccess = document.getElementById('success')
            var divPrincipal = document.getElementById('principal')

            divPrincipal.style.display = 'none'
            divLoading.style.display = 'flex'           
            
            var form = document.getElementById('form-contact');
            form.reset()
                    
            setTimeout(function() {
                divLoading.style.display = 'none'
                divSuccess.style.display = 'flex'
                console.log('concluido!');
            }, 3500);
        }
    })
    .catch(error => console.error('Erro:', error));
}

function validarNome(nome) {
    return nome.trim() !== '' && /^[A-Za-zÁÉÍÓÚáéíóúÑñÇç\s]+$/.test(nome);
}

function validarEmail(email) {
    var regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    return email.trim() !== '' && regex.test(email);
}

function validarTelefone(numero) {
    var regex = /^\(\d{2}\) \d{5}-\d{4}$/;
    return regex.test(numero);
}

document.getElementById('form-contact').addEventListener('submit', function(event) {
    event.preventDefault(); 

    var nome = document.getElementById('nome').value;
    if(!validarNome(nome)){
        document.getElementById('form-warning-wrapper').style.display='block';
        if(nome==''){
            document.getElementById('form-warning-message').innerText='Informe seu nome';
        }
        else{
            document.getElementById('form-warning-message').innerText='Nome invalido';
        }
    }
    else{
        var empresa = document.getElementById('empresa').value;
        if(!validarNome(empresa)){
            document.getElementById('form-warning-wrapper').style.display='block';
            if(empresa==''){
                document.getElementById('form-warning-message').innerText='Informe a empresa';
            }
            else{
                document.getElementById('form-warning-message').innerText='Nome da empresa invalido';
            }
        }
        else{
            var email = document.getElementById('email').value;
            if(!validarEmail(email)){
                document.getElementById('form-warning-wrapper').style.display='block';
                if(email==''){
                    document.getElementById('form-warning-message').innerText='Informe o email';
                }
                else{
                    document.getElementById('form-warning-message').innerText='Email invalido';
                }
            }
            else{
                var telefone = document.getElementById('celular').value;
                if(!validarTelefone(telefone)){
                    document.getElementById('form-warning-wrapper').style.display='block';
                    if(telefone==''){
                        document.getElementById('form-warning-message').innerText='Informe o telefone';
                    }
                    else{
                        document.getElementById('form-warning-message').innerText='telefone invalido';
                    }
                }
                else{
                    var form = document.getElementById('form-contact');
                    var dados = new FormData(form);
                    enviarDados(dados);
                }
            }
        }
    }
});

$(document).ready(function() {
    $('#celular').mask('(00) 00000-0000');
});

