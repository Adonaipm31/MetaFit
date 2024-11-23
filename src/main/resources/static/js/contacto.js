//Envio Formulario Contacto
    emailjs.init('M2Rhl0hf6HH_PHpmZ')
    const btn = document.getElementById('button-contacto');

    document.getElementById('form')
    .addEventListener('submit', function(event) {
    event.preventDefault();

    

    const serviceID = 'default_service';
    const templateID = 'template_cj0ftzi';

    emailjs.sendForm(serviceID, templateID, this)
        .then(() => {

            Swal.fire({
                icon: 'success',
                title: 'Se envió el mensaje',
                timer: 2500,
                showConfirmButton: false
              })

        window.location = "contacto.html";
		window.location.href = "contacto";

        }, (err) => {
        btn.value = 'Send Email';
        alert(JSON.stringify(err));
        });
    });


