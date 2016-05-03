package lt.product.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.product.command.CreateProductParameterValueCommand;
import lt.product.command.ModifyProductParameterValueCommand;

import org.hibernate.annotations.DynamicUpdate;

//SKU规格，如车型、颜色
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "PRODUCT_PARAMETER_VALUE")
public class ProductParameterValue extends StringIdBaseEntity {


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parameter_id")
	private ProductParameter parameter;

	@Column(name = "p_value")
	private String value;

	public void create(CreateProductParameterValueCommand command,
			 ProductParameter parameter) {
		
		setId(UUIDGenerator.getUUID());

		setParameter(parameter);
		setValue(command.getValue());
	}
	
	public void modify(ModifyProductParameterValueCommand command,ProductParameter parameter){
		
		setParameter(parameter);
		setValue(command.getValue());
	}


	public ProductParameter getParameter() {
		return parameter;
	}

	public void setParameter(ProductParameter parameter) {
		this.parameter = parameter;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
