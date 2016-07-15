$.ajaxSetup({
    complete: function(xhr,status) {
        var sessionStatus = xhr.getResponseHeader('sessionstatus');
        if(sessionStatus == 'timeout') {
        	confirm("会话失效，请重新登录",function(){
   			 window.location.href= "/login"; 
    		});
        }
    }
});

function confirm(content,callback){
	new PNotify({
	    title: '提示',
	    text: content,
	    icon: 'glyphicon glyphicon-info-sign',
	    type : 'info',
	    styling: 'bootstrap3',
	    addclass: "stack-modal",
	    hide: false,
	    confirm: {
	        confirm: true,
	        buttons: [{
	            text: '确定',
	            addClass: 'btn-primary',
	            click: function(notice) {
	                notice.remove();
	                callback();
	            }
	        },
	        null]
	    },
	    buttons: {
	        closer: false,
	        sticker: false
	    },
	    history: {
	        history: false
	    }
	});
}




