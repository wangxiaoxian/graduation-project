<table class="tableEdit" style="width:580px;" cellspacing="0" border="0" cellpadding="0">
	<#assign i = 0>
	<#list form.fields as field>
	<#assign i = i + 1>
	<#if field_index % 2 = 0>
	<tr>
	</#if>
		<td class="tdEditLabel" >${field.fieldLabel}</td>			
		<td class="tdEditContent"><#include "${field.fieldInput.template}">
		</td>
	<#if i = 2 || !field_has_next>
	<#assign i = 0>
	</tr>
	</#if>
	</#list>
</table>