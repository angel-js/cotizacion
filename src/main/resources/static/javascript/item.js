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