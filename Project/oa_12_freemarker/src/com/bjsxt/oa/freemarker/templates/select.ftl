<select name="props['${field.fieldName}']">
	<#list field.items  as item>
	<option value="${item.value}">${item.label}</option>
	</#list>
</select>