/* Add here all your JS customizations */

PNotify.prototype.options.delay = 3000;

String.prototype.format = function() {
    var formatted = this;
    for( var arg in arguments ) {
        formatted = formatted.replace("{" + arg + "}", arguments[arg]);
    }
    return formatted;
};