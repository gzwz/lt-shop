package lt.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.base.command.CreateImageCommand;
import lt.base.qo.ImageQO;
import lt.base.service.ImageService;
import lt.product.command.CreateProductExteriorCommand;
import lt.product.command.ModifyProductExteriorCommand;
import lt.product.entity.Product;
import lt.product.entity.ProductExterior;
import lt.product.qo.ProductExteriorQO;
import lt.product.qo.ProductQO;
import gzlazypack.common.component.BaseDao;
@Service
@Transactional
public class ProductExteriorService extends BaseDao<ProductExterior, ProductExteriorQO>{

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ProductService productService;
	
	public ProductExterior createProductExterior(CreateProductExteriorCommand command){
			
			ProductQO productQO=new ProductQO();
			productQO.setId(command.getProductId());
			Product product=productService.queryUnique(productQO);
		
			ProductExterior productExterior=new ProductExterior();
			productExterior.create(command, product);
			save(productExterior);
			
			// 图片处理
			Map<String, String> specImageMap = new HashMap<String, String>();
			if (StringUtils.isNoneBlank(command.getImages())) {
				for (int i = 0; i < command.getImages().split(",").length; i++) {
					specImageMap.put("product_image" + i, command.getImages()
							.split(",")[i]);
				}

				CreateImageCommand imgCommand = new CreateImageCommand();
				imgCommand.setSpecImageMap(specImageMap);
				List<CreateImageCommand> createImageCommandList = new ArrayList<CreateImageCommand>();
				createImageCommandList.add(imgCommand);
				command.setCreateImageCommandList(createImageCommandList);
				// 创建商品图片
				if (CollectionUtils.isNotEmpty(command.getCreateImageCommandList())) {
					int index = 0;
					for (CreateImageCommand createImageCommand : command
							.getCreateImageCommandList()) {
						createImageCommand.setDomainLinkId(productExterior.getId());
						createImageCommand.setDomainLinkName(productExterior.getType());
						createImageCommand.setDomainLinkType("product_image");
						createImageCommand.setTitle(productExterior.getName());
						createImageCommand.setSort(index);
						imageService.createImage(createImageCommand);
						index++;
					}
				}
			}
			return productExterior;
	}
	
	
	public ProductExterior modifyProductExterior(ModifyProductExteriorCommand command){
		
		ProductQO productQO=new ProductQO();
		productQO.setId(command.getProductId());
		Product product=productService.queryUnique(productQO);
	
		ProductExterior productExterior=get(command.getProductExteriorId());
		productExterior.modfiy(command, product);
		
		
		ImageQO imgeQO = new ImageQO();
		imgeQO.setDomainId(productExterior.getId());
		imageService.delete(imageService.queryUnique(imgeQO));
		
		// 图片处理
		Map<String, String> specImageMap = new HashMap<String, String>();
		if (StringUtils.isNoneBlank(command.getImages())) {
			for (int i = 0; i < command.getImages().split(",").length; i++) {
				specImageMap.put("product_image" + i, command.getImages()
						.split(",")[i]);
			}
			

			CreateImageCommand imgCommand = new CreateImageCommand();
			imgCommand.setSpecImageMap(specImageMap);
			List<CreateImageCommand> createImageCommandList = new ArrayList<CreateImageCommand>();
			createImageCommandList.add(imgCommand);
			command.setCreateImageCommandList(createImageCommandList);
			// 创建商品图片
			if (CollectionUtils.isNotEmpty(command.getCreateImageCommandList())) {
				int index = 0;
				for (CreateImageCommand createImageCommand : command
						.getCreateImageCommandList()) {
					createImageCommand.setDomainLinkId(productExterior.getId());
					createImageCommand.setDomainLinkName(productExterior.getType());
					createImageCommand.setDomainLinkType("product_image");
					createImageCommand.setTitle(productExterior.getName());
					createImageCommand.setSort(index);
					imageService.createImage(createImageCommand);
					index++;
				}
			}
		}
		
		update(productExterior);
		return productExterior;
	}
	@Override
	protected Criteria buildCriteria(Criteria criteria, ProductExteriorQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<ProductExterior> getEntityClass() {
		// TODO Auto-generated method stub
		return ProductExterior.class;
	}

}
