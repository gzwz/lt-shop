/**
 * table 初始化js
 */
var Menu = function() {
	
	var parentIds = "0", pId = "", topHtml = "", slideHtml = "", contextPath=""; 
	
	/**
	 * 得到一个树节点的所有父节点
	 * @param {Object} data json数据
	 * @param {Object} parentId 父节点
	 * @param {Object} currentNode 当前节点
	 * @param {Object} containCurrentNode 是否包含当前节点
	 * @param {Object} parentIds 要放回的parentId
	 */
	var getParents = function (data, parentId, currentNode, containCurrentNode){
		for (var key in data){
			if (parentId == 0){
				parentIds = "0";
			}
			if  (currentNode == data[key].id){
				if (containCurrentNode){
					parentIds += "," + parentId + "," + data[key].id;
				}
				pId = parentIds;
			}

			if (data[key].children != undefined && data[key].children.length > 0){
				if (!isContain(parentIds, parentId)){
					parentIds += "," + parentId;
				}
				getParents(data[key].children, data[key].id, currentNode, containCurrentNode);
			}
		}
	}
	
	var isContain = function(parentIds, parentId){
		var array = parentIds.split(",");
		for (var key in array){
			if (array[key] == parentId){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 得到顶部按钮 
	 * @param {Object} data
	 * @param {Object} parentIds
	 */
	var getTopHtml = function(data, parentIds){
		var html = "";
		//只遍历顶部json
		for (var key in data){
			var str;
			if (isContainId(data[key].id, parentIds)){
				str = "<li class='classic-menu-dropdown active'><a href='" + contextPath + data[key].url + "'>" + data[key].text + "<span class='selected'></span></a></li>";
			}else{
				str = "<li class='classic-menu-dropdown'><a href='" + contextPath + data[key].url + "'>" + data[key].text + "<span class='selected'></span></a></li>";
			}
			html += str;
		}
		return html;
	}
	
	
	var getParent = function(){
		
	}
	
	/**
	 * 得到slider 
	 * @param {Object} data
	 * @param {Object} parentIds
	 */
	var getSliderHtml = function(data, parentIds){
		var html = "";
		for (var key in data){
			//从第二层开始遍历  目前只支持三层 后续扩展
			if (data[key].id == parentIds.split(",")[1] && data[key].children != undefined && data[key].children.length > 0){//只遍历当前节点下的
				var children = data[key].children;
				for(var child in children){//第二层
					var str = "";
					if (isContainId(children[child].id, parentIds)){ //第二层不设置url
						str += "<li class='start active open'><a href='javascript:;'><i class='fa " + children[child].icon + "'></i><span class='title'>" + children[child].text + "</span><span class='arrow open'></span><span class='selected'></span></a>";
					}else{
						str += "<li><a href='javascript:;'><i class='fa " + children[child].icon + "'></i><span class='title'>" + children[child].text + "</span><span class='arrow open'></span><span class='selected'></span></a>";
					}
					
					if (children[child].children != undefined && children[child].children.length > 0){
						var third = children[child].children;
						str += "<ul class='sub-menu'>";
						for (var t in third){
							if (isContainId(third[t].id, parentIds)){ 
								str += "<li class='active'><a href='" + contextPath + third[t].url + "'>" + third[t].text + "</a></li>";
							}else{
								str += "<li><a href='" + contextPath + third[t].url + "'>" + third[t].text + "</a></li>";
							}
						}
						str += "</ul>";
					}
					
					str += "</li>";
					html += str;
				}
			}
			
		}
		return html;
	}
	
	/**
	 * 判断是否包含指定的id
	 * @param {Object} id
	 * @param {Object} Ids
	 */
	var isContainId = function(id, ids){
		var arr = ids.split(",");
		for (var i in arr){
			if (id == arr[i]){
				return true;
			}
		}
		return false;
	}
	
	var getParentId = function(data, currentId){
		for (var key in data){
			if (currentId == data[key].id){//如果是root 返回 0
				return 0;
			}else{
				//判断当前node是否有子node
				if (data[key].children != undefined && data[key].children.length > 0){
					var children = data[key].children;
					for (var child in children){
						if (children[child].id == currentId){
							return data[key].id;
						}else{
							var third = children[child].children;
							for (var t in third){
								if (third[t].id == currentId){
									return children[child].id;
								}
							}
						}
					}
				}
				
			}
		}
	}
	
	

	return {
		init: function(options) {
//			options = $.extend(true, {
//				url: "",
//				contextPath: "",
//				currentMenu: 1,
//				parentIds: 0
//			}, options);
//			if (!(options.contextPath == undefined || options.contextPath == null || options.contextPath == "")){
//				contextPath = options.contextPath;
//			}
//			document.getElementById("slide_html")
//			//请求数据
//			$.getJSON(options.url, {
//				tags: 'cat',
//				tagmode: 'any',
//				format: 'json'
//			}, function(data) {
//				getParents(data, 0, options.currentMenu, true);
//				options.parentIds = pId;
//				topHtml = getTopHtml(data, options.parentIds);
//				
//				slideHtml = getSliderHtml(data, options.parentIds);
//				
//				$("#top_nav").html(topHtml);
//				console.log(slideHtml);
//				$("#slide_html").append(slideHtml);
//				
//			});

		}
	};

}();