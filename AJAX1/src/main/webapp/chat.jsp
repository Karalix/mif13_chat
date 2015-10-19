<!DOCTYPE html>
<html>
    <head>
        <script src="http://localhost:8080/Chat/ajax.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    </head>

    <body>
        <% String user = request.getParameter("login");%>
        <% String room = request.getParameter("room");%>
        <h1>Chat</h1>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><%= room %></h2>
            </div>
            <div class="panel-body" id="listMessages" style="height: 250px;overflow: auto;">

            </div>
        </div>
        <div class="form-inline">
            <input type="text" class="form-control" onKeyPress="keyPressed(event,this)" id="textToSend" placeholder="Votre texte ici">
        </div>
        <script>
            function keyPressed(e, input) {
                var code = (e.keyCode ? e.keyCode : e.which);
                   if(code == 13) { //Enter keycode
                     sendMessage();
                     document.getElementById("textToSend").value = "" ;
                 }
            }
            
            function sendMessage() {
                var messageText = document.getElementById("textToSend").value;
                var parameters = "texte="+messageText+"&login=<%= user %>&room=<%= room %>&but=msg";
                sendRequestAsynchroneously("POST", "http://localhost:8080/Chat/Conversations/<%= room %>/Messages", parameters);
            }
            function loadMessages() {
                    loadXMLAsynchroneously("GET", "http://localhost:8080/Chat/Conversations/<%= room %>/Messages", null, "listMessages");
            }
            setInterval(function(){ loadMessages(); }, 2000);
        </script>
    </body>
</html>