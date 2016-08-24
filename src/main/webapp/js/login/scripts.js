
jQuery(document).ready(function() {

    $('.page-container form .btn_login').click(function(){
        var username = $(this).parent().find('.username').val();
        var password = $(this).parent().find('.password').val();
        var validatecode = $(this).parent().find('.validatecode').val();
        if(username == '') {
        	$(this).parent().find('.error').fadeOut('fast', function(){
                $(this).css('top', '27px');
            });
        	$(this).parent().find('.error').fadeIn('fast', function(){
                $(this).parent().find('.username').focus();
            });
            return false;
        }
        if(password == '') {
        	$(this).parent().find('.error').fadeOut('fast', function(){
                $(this).css('top', '96px');
            });
        	$(this).parent().find('.error').fadeIn('fast', function(){
                $(this).parent().find('.password').focus();
            });
            return false;
        }
        if(validatecode == '') {
        	$(this).parent().find('.error').fadeOut('fast', function(){
        		$(this).css('top', '165px');
        	});
        	$(this).parent().find('.error').fadeIn('fast', function(){
        		$(this).parent().find('.validatecode').focus();
        	});
        	return false;
        }
        
        $.ajax({
        	url:"/login.do",
        	type:"POST",
        	data:$(this).parent().serialize(),
        	complete:function(XMLHttpRequest, textStatus){
        		var seponseText = XMLHttpRequest.responseText;
        		if(seponseText == "userError") {
        			
        		} else if(seponseText == "validateError") {
        			
        		} else if(seponseText == "validateError") {
        			
        		} else {
        			
        		}
        	}
        });
        
    });

    $('.page-container form .username, .page-container form .password, .page-container form .validatecode').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });
    
    $('.page-container .imgvalidatecode').click(function(){
    	$(this).attr('src', $(this).attr('src') + "?temp=" + Math.random());
    });

});
