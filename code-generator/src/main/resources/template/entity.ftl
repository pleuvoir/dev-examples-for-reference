package cn.com.cicpay.leopard.model.hbm;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "${tableName?lower_case}")
public class ${entityName} implements Serializable {
	
	
	
	<#list columnExtendList as columnExtend>
	<#if "${columnExtend.field}" == "${primaryKey}">
    @Id
	@GeneratedValue(generator = "${entityName?uncap_first}Seq")
	@GenericGenerator(name="${entityName?uncap_first}Seq",strategy="uuid")
	</#if>
	<#if "${columnExtend.columnTypeName}" == "BLOB">
	@Lob
    @Basic(fetch = FetchType.LAZY)
	</#if>
	@Column(name = "${columnExtend.columnName?lower_case}", <#if "${columnExtend.convertedType}" == "byte[]">columnDefinition = "BLOB",</#if><#if "${columnExtend.convertedType}" == "BigDecimal">precision = ${columnExtend.precision}, scale = ${columnExtend.scale},</#if><#if "${columnExtend.convertedType}" == "String">length = ${columnExtend.columnDisplaySize},</#if> nullable = ${columnExtend.isNullable})
	<#if "${columnExtend.columnTypeName}" == "TIMESTAMP">
	@Temporal(TemporalType.TIMESTAMP)
	</#if>
	private ${columnExtend.convertedType} ${columnExtend.field};
	
	</#list>
	
	
	<#list columnExtendList as columnExtend>
	public ${columnExtend.convertedType} get${columnExtend.field?cap_first}() {
		return ${columnExtend.field};
	}
	public void set${columnExtend.field?cap_first}(${columnExtend.convertedType} ${columnExtend.field}) {
		this.${columnExtend.field} = ${columnExtend.field};
	}
	</#list>

	
	
}
	