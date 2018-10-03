var wsUri = "ws://";
var socketendpoint = "/WebSocketConcurrentTaskFlowEdit/websocket/taskflow/";
var websocket;
var userId;
var taskFlowId;

// Gets the WebSocket URI 
function getWSUri() {
  var url = window.location.href;
  var arr = url.split("/");
  url= wsUri + arr[2] + socketendpoint;
  return url;
}

// Connects to the WebSocket
function connectTaskFlowSocket(user, tfId) {  
  if ('WebSocket' in window){
    websocket = new WebSocket(getWSUri() + user + "/" + tfId);
    websocket.onmessage = onMessage;   
    websocket.onerror = onError;
    websocket.onclose = onClose;
    websocket.onopen = onOpen;
    userId = user;
    taskFlowId = tfId;
    console.log('socket opened !');
  } else {
    console.log('websocket not supported...!')
  }
}

//on error event
function onError(evt) {
  console.log('error :' + evt);
  cancelKeepAlive();
}

//on close event
function onClose(evt) {
  console.log('websocket closed :' + evt.code + ":" + evt.reason);
  cancelKeepAlive();
}

// on open event
function onOpen(evt) {
    keepAlive();
}

// Handle the message received by the Web Socket
function onMessage(evt) {
  var message = JSON.parse(evt.data);
  $.notify("User: " + message.userId + " is editing this also!!!",{position:"top center",className:"warn"});
  // Inform by using JSF Faces Message from Server side
//  var comp = AdfPage.PAGE.findComponentByAbsoluteId('d1');
//  AdfCustomEvent.queue(comp, "SocketMessageReceive",{userId: message.userId}, true);         
//  evt.cancel();
  
}

// Keep alive the Web Socket
var timerId = 0; 
function keepAlive() { 
    var timeout = 20000;  
    if (websocket.readyState == websocket.OPEN) {  
        websocket.send(JSON.stringify({userId: userId, taskFlowId: taskFlowId}));
    }  
    timerId = setTimeout(keepAlive, timeout);  
}  
function cancelKeepAlive() {  
    if (timerId) {  
        clearTimeout(timerId);  
    }  
}
