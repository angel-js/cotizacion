/*  function agregarItem() {
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
} */

var index = 0;

function agregarFila() {
  var itemRowTemplate = document.querySelector("#item-row-template");
  var itemTableBody = document.querySelector("#item-table-body");
  var newRow = itemRowTemplate.cloneNode(true);
  newRow.classList.remove("d-none");
  var index = itemTableBody.querySelectorAll(".item-row").length;
  newRow.innerHTML = newRow.innerHTML.replace(/__index__/g, index);
  itemTableBody.appendChild(newRow);
  document.querySelector("#item-count").value = index + 1;
}

function eliminarItem(btn) {
  // Elimina la fila del botón presionado
  var row = btn.parentNode.parentNode;
 row.parentNode.removeChild(row);
}

function actualizarNombresItems() {
  var rows = document.querySelectorAll('.item-row');
  rows.forEach(function(row, index) {
    row.querySelector('input[name^="items"]').setAttribute('name', 'items[' + index + ']');
  });
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
