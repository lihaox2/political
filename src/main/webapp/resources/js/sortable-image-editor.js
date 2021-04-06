var SortableImageEditor;
(function () {
	SortableImageEditor = function (options) {
		this.container = $("#"+options.id);
		this.imageSelector = $("#"+options.imageSelectorId);
		this.imageInputName = options.imageInputName;
		this.imagePathInputName = options.imagePathInputName;
		this.maxImageCount = options.maxImageCount;
		this.imageCount = 0;
		$("#"+options.id).sortable();
		
		var that = this;
		$( ".image-delete" ).each(function(index) {
			$(this).on("click", function(){
				$(this).parent().remove();
				that.imageCount--;
				that.imageSelector.show();
			});
		});
		this.fileInput = null;
	};
	
	SortableImageEditor.prototype = {
		constructor: SortableImageEditor,
		
		addImage: function() {
			if(this.imageCount >= this.maxImageCount) {
				return false;
			}

			var $imageContainer = $("<li></li>");
			
			this.fileInput = this._createFileInput($imageContainer);
			this.fileInput.click();
			
		},
		
		_createFileInput: function(imageContainer) {
			var that = this;
			return $("<input name='"+this.imageInputName+"' type='file' style='display:none'/>").bind('change',function(e) {
				that._fileInputChange($(this)[0].files[0], imageContainer);
			});
		},
		
		_fileInputChange: function(imageFile, imageContainer) {
			var that = this;
			itemImageError = false;
			$("#imageSizeError").hide();
			$("#imageTypeError").hide();
			
			// check if file is a image
			var fileType = imageFile["type"];
			var validImageTypes = ["image/jpg", "image/jpeg", "image/png"];
			if ($.inArray(fileType, validImageTypes) < 0) {
				$("#imageTypeError").show();
				return false;
			} else {
				$("#imageTypeError").hide();
			}
			
			//check file size
			fileSize = (imageFile.size/1024)/1000;
			if(fileSize > 6) {
				$("#imageSizeError").show();
				return false;
			} else {
				$("#imageSizeError").hide();
			}
			
			var objUrl = getObjectURL(imageFile);
			if(objUrl) {
				var previewImage = document.createElement( "img" );
				$(previewImage).attr("src",objUrl);
				imageContainer.append(previewImage);
				this.container.append(imageContainer);
				
				var $imageDelteIcon = $("<span class='lnr lnr-cross image-delete'></span>").bind('click',function(e){
					imageContainer.remove();
					that.imageCount--;
					that.imageSelector.show();
				});
			
				imageContainer.append($imageDelteIcon);
				imageContainer.append(this.fileInput);
				imageContainer.append($("<input type='hidden' name='"+this.imagePathInputName+"' value=' '/>"));
				that.container.append(imageContainer);
				
				that.imageCount++;
				
				if (that.imageCount >= that.maxImageCount) {
					that.imageSelector.hide();
				}
			}
		}
	};
})();

function getObjectURL(file) {
	var url = null ;
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}