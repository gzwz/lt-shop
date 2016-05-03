package lt.pc.web.contorller.index;

import java.util.ArrayList;
import java.util.List;

import gzlazypack.common.util.JSONUtils;

import javax.servlet.http.HttpServletRequest;

import lt.pc.web.contorller.BaseController;
import lt.sitepc.DTO.BottomMenuDTO;
import lt.sitepc.DTO.BottomMenuParentDTO;
import lt.sitepc.entity.BottomMenu;
import lt.sitepc.entity.MenuContents;
import lt.sitepc.qo.BottomMenuQO;
import lt.sitepc.qo.MenuContentsQO;
import lt.sitepc.service.BottomMenuService;
import lt.sitepc.service.MenuContentsService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/menuContent")
public class MenuContentsController extends BaseController{
	
	@Autowired
	private BottomMenuService bottomMenuService;
	
	@Autowired
	private MenuContentsService menuContentsService;
	
	@RequestMapping(value="getBottomMenu")
	@ResponseBody
	public String getBottomMenu(HttpServletRequest request){
		
		return JSONUtils.c(getListBottomMenu(request));
	}
	
	
	
	@RequestMapping(value="getMenuContent")
	public String getMenuContent(HttpServletRequest request,MenuContentsQO qo,Model model){
		
		if(StringUtils.isNotBlank(qo.getBottomMenuId())){
			qo.setBottomMenuQO(new BottomMenuQO());
			qo.getBottomMenuQO().setId(qo.getBottomMenuId());
		}
		MenuContents contents=null;
		qo.setOrderBy(1);
		try {
			contents=menuContentsService.queryUnique(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("bottomList", getListBottomMenu(request));
		model.addAttribute("contents", contents);
		
		return "/help/menuContent.html";
	}
	
	
	
	public List<BottomMenuParentDTO> getListBottomMenu(HttpServletRequest request){
		BottomMenuQO qo=new BottomMenuQO();
		qo.setFetchBottomMenu(true);
		qo.setOrderBy(1);
		List<BottomMenu> bmList=bottomMenuService.queryList(qo);
		/**
		 * dto转换
		 */
		List<BottomMenuParentDTO> bmtList=new ArrayList<BottomMenuParentDTO>();
		for (BottomMenu bottomMenu : bmList) {
			if(StringUtils.equals(bottomMenu.getId(), bottomMenu.getParent().getId())){
				BottomMenuParentDTO bottomMenuParentDTO=new BottomMenuParentDTO();
				bottomMenuParentDTO.setId(bottomMenu.getId());
				bottomMenuParentDTO.setIcon(bottomMenu.getIcon());
				bottomMenuParentDTO.setName(bottomMenu.getName());
				

				List<BottomMenuDTO> childrenDTOList=new ArrayList<BottomMenuDTO>();
				BottomMenuQO childrenQO=new BottomMenuQO();
				childrenQO.setParentQO(new BottomMenuQO());
				childrenQO.getParentQO().setId(bottomMenu.getId());
				List<BottomMenu> childrenList=bottomMenuService.queryList(childrenQO);
				for (BottomMenu children : childrenList) {
					if(!StringUtils.equals(children.getName(), children.getParent().getName())){
						BottomMenuDTO bottomMenuDTO=new BottomMenuDTO();
						bottomMenuDTO.setId(children.getId());
						bottomMenuDTO.setChildrenName(children.getName());
						childrenDTOList.add(bottomMenuDTO);
					}
					
				}
				bottomMenuParentDTO.setBottomMenuDTO(childrenDTOList);
				bmtList.add(bottomMenuParentDTO);
			}
		}
		return bmtList;
	}
	

}
