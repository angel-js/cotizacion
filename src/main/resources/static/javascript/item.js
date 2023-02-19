    function agregarItem() {
    var itemsSection = document.getElementById("items-section");
    var itemRowTemplate = document.getElementById("item-row-template");
    var newItemRow = itemRowTemplate.cloneNode(true);
    newItemRow.getElementsByClassName("form-control")[0].value = "";
    newItemRow.getElementsByClassName("form-control")[1].value = "";
    itemsSection.insertBefore(newItemRow, itemsSection.lastChild);
    newItemRow.classList.remove("d-none");
}

    function eliminarItem(button) {
    var itemRow = button.parentNode.parentNode;
    itemRow.parentNode.removeChild(itemRow);
}
/**
function buscarSupervisoresPorDepartamento() {
    var departamentoId = $('#Local').val();
    if (departamentoId) { // Verificar que el valor no es nulo o undefined
        $.ajax({
            url: "/user/supervisoresPorDepartamento",
            data: { idDepartamento: departamentoId },
            success: function(data) {
                var supervisorSelect = $("#supervisor");
                supervisorSelect.empty();
                $.each(data, function(index, supervisor) {
                    supervisorSelect.append($('<option>', {
                        value: supervisor.id,
                        text: supervisor.nombre
                    }));
                });
            }
        });
    }
}

function buscarSupervisoresPorDepartamento(idDepartamento) {
    $.ajax("/user/supervisoresPorDepartamento?idDepartamento=" + idDepartamento, function(data) {
        var supervisorSelect = $("#supervisor");
        supervisorSelect.empty();
        $.each(data, function(index, supervisor) {
            supervisorSelect.append($('<option>', {
                value: supervisor.id,
                text: supervisor.nombre
            }));
        });
    });
}**/
