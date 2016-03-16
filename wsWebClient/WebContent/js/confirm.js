//callback handler for form submit
$("#confirm").submit(function(e)
{
    var postData = $(this).serializeArray();
    var formURL = $(this).attr("action");
    $.ajax(
    {
        url : formURL,
        type: "POST",
        data : postData,
        success:function(data, textStatus, jqXHR) 
        {
            location.reload()
        },
        error: function(jqXHR, textStatus, errorThrown) 
        {
            window.alert(textStatus)
        }
    });
    e.preventDefault(); //STOP default action
    e.unbind(); //unbind. to stop multiple form submit.
});
 
$("#confirm").submit(); //Submit  the FORM