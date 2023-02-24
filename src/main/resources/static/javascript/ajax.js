<script type="text/javascript">
    function submitForm() {
        // Obtener los datos del formulario
        var form = document.getElementById("form-trabajo");
        var formData = new FormData(form);
        var object = {};
        formData.forEach(function(value, key){
            object[key] = value;
        });

        // Enviar los datos en formato JSON
        var items = document.getElementsByName("items");
        var xhr = new XMLHttpRequest();
        xhr.open("POST", form.getAttribute("action"), true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                window.location.href = xhr.responseText;
            }
        };
        xhr.send(JSON.stringify(object));
    }
</script