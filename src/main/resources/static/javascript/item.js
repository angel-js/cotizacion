
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
