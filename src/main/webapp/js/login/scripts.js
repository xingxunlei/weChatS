
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
        			$('.alert-info').fadeOut('fast', function(){
                        $(this).text('用户名或密码错误,请重新输入');
                    });
        			$('.alert-info').fadeIn('fast', function(){
                        $(this).parent().find('.username').focus();
                        $('.page-container .imgvalidatecode').click();
                    });
        		} else if(seponseText == "validateError") {
        			$('.alert-info').fadeOut('fast', function(){
                        $(this).text('验证码输入错误,请重新输入');
                    });
        			$('.alert-info').fadeIn('fast', function(){
                        $(this).parent().find('.validatecode').focus();
                        $('.page-container .imgvalidatecode').click();
                    });
        		} else if(seponseText == "loginSuccess") {
        			$('.alert-info').fadeOut('fast', function(){
                        $(this).text('登录成功');
                    });
        			$('.alert-info').fadeIn('fast', function(){});
        		} else {
        			$('.alert-info').fadeOut('fast', function(){
                        $(this).text('系统繁忙,请稍后再试');
                    });
        			$('.alert-info').fadeIn('fast', function(){
                        $(this).parent().find('.validatecode').focus();
                        $('.page-container .imgvalidatecode').click();
                    });
        		}
        	}
        });
        
    });

    $('.page-container form .username, .page-container form .password, .page-container form .validatecode').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
        $(this).parent().find('.alert-info').fadeOut('fast');
    });
    
    $('.page-container .imgvalidatecode').click(function(){
    	$(this).attr('src', $(this).attr('src') + "?temp=" + Math.random());
    });

});
