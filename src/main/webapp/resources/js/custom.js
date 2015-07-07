/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showDialog(name) {
    var dialog = "#" + name;
    $(dialog).modal('show').draggable();
}

function hideDialog(name) {
    var dialog = "#" + name;
    $(dialog).modal('hide');
}
