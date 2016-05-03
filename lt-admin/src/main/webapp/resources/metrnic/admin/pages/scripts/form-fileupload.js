var FormFileUpload = function () {
    return {
        init: function (options) {
        	options = $.extend(true, {
                url: "",
                uploadContainer: "#fileupload", 
                fileContainer: "#files",
                icon: "" // put icon before the message
            }, options);
        	
        	
			var uploadButton = $('<button/>')
		            .addClass('btn btn-primary upload').prop('type', 'button')
		            .prop('disabled', true).append('<i class="fa fa-upload"></i><span>处理中..</span>')
		            .on('click', function () {
		                var $this = $(this),
		                    data = $this.data();
		                $this.off('click')
		                    .text('取消')
		                    .on('click', function () {
		                        $this.remove();
		                        data.abort();
		                    });
		                data.submit().always(function () {
		                    $this.remove();
		               });
	            });
		            
		   var fu = $(options.uploadContainer).fileupload({
			        url: options.url,
			        dataType: 'json',
			        autoUpload: false,
			        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
			        maxFileSize: 5000000, // 5 MB
			        disableImageResize: /Android(?!.*Chrome)|Opera/.test(window.navigator.userAgent),
			        previewMaxWidth: 195,
			        previewMaxHeight: 140,
			        previewCrop: true
			    }).on('fileuploadadd', function (e, data) {//图片添加
			        data.context = $("<div class='col-sm-6 col-md-3 img-box'><div/>").appendTo('#files');
			        $.each(data.files, function (index, file) {
			            var node = $("<div class='caption'></div>");
			            if (!index) {
			                node.append(uploadButton.clone(true).data(data));
			            }
			            node.appendTo(data.context);
			            var delBtn = $('<button/>').addClass('btn red delete').prop('type', 'button').on("click", function(){
			            	//图片删除事件
			            	var parentNode = $(this).parent(".caption").parent(".img-box");
			            	parentNode.remove();
			            }).append('<i class="fa fa-trash"></i><span>删除</span>');
			            node.append(delBtn);
//			            '<button type="button" class="btn red delete"><i class="fa fa-trash"></i><span>删除</span></button>'.appendTo(node);
			        });
			    }).on('fileuploadsubmit', function(e, data){
			    	//alert("fileuploadsubmit");
			    }).on('fileuploadsend', function(e, data){
			    	//alert("fileuploadsend");
			    }).on('fileuploadalways', function(e, data){
			    	//alert("fileuploadalways");
			    }).on('fileuploadprocessalways', function (e, data) {//增加图片预览
			        var index = data.index,
			            file = data.files[index],
			            node = $(data.context.children()[index]);
			        if (file.preview) {
			            node.addClass('thumbnail').prepend(file.preview);
			        }
			        if (file.error) {
			            node.append($('<span class="text-danger"/>').text(file.error));
			        }
			        if (index + 1 === data.files.length) {
			            data.context.find('button.upload').html("").append('<i class="fa fa-upload"></i><span>上传</span>')
			                .prop('disabled', !!data.files.error);
			        }
			    }).on('fileuploadprogressall', function (e, data) {
			        var progress = parseInt(data.loaded / data.total * 100, 10);
			        $('#progress .progress-bar').css('width', progress + '%');
			    }).on('fileuploaddone', function (e, data) {//上传完成
			    	var key = JSON.stringify(data.result);
			    	if (key != undefined && key != null && key != ""){
			    		var hiddenInput = $(options.uploadContainer).siblings(".image-input").get(0);
			    		var hiddenValue = $(hiddenInput).val();
			    		if (hiddenValue == ""){
			    			hiddenValue = "[" + key + "]";
			    		}else{
			    			var temp = hiddenValue.substring(0, hiddenValue.length -1);
			    			hiddenValue = temp + "," + key + "]";
			    		}
			    		$(hiddenInput).val(hiddenValue);
			    	}
			    	
//			        $.each(data.result.files, function (index, file) {
//			            if (file.url) {
//			                var link = $('<a>').attr('target', '_blank')
//			                    				.prop('href', file.url);
//			                $(data.context.children()[index]).wrap(link);
//			            } else if (file.error) {
//			                var error = $('<span class="text-danger"/>').text(file.error);
//			                $(data.context.children()[index])
//			                    .append('<br>')
//			                    .append(error);
//			            }
//			        });
			    }).on('fileuploadfail', function (e, data) {//上传失败
//			        $.each(data.files, function (index) {
//			            var error = $('<span class="text-danger"/>').text('上传失败.');
//			            $(data.context.children()[index])
//			                .append('<br>')
//			                .append(error);
//			        });
			    }).prop('disabled', !$.support.fileInput)
			        .parent().addClass($.support.fileInput ? undefined : 'disabled');
			        
			    var uploadAll = $(options.uploadContainer).parent().siblings(".upload-all");
			    var deleteAll = $(options.uploadContainer).parent().siblings(".delete-all")
			   	uploadAll.click(function(){
			   		var filesList = $(options.fileContainer).find(".upload");
					filesList.click();
				});  
				deleteAll.click(function(){
					$(options.fileContainer).html("");
					var hiddenInput = $(options.uploadContainer).siblings(".image-input").get(0);
			    	$(hiddenInput).val("");
//					var key = data.result.key;
//			    	if (key != undefined && key != null && key != ""){
//			    		var hiddenInput = $(options.uploadContainer).siblings(".image-input").get(0);
//			    		var hiddenValue = $(hiddenInput).val();
//			    		if (hiddenValue == ""){
//			    			hiddenValue = key;
//			    		}else{
//			    			hiddenValue += "," + key;
//			    		}
//			    	}
				});
		            
           	
       }

    };

}();