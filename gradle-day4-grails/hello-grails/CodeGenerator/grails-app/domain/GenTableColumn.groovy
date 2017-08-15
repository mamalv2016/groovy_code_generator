class GenTableColumn{ 
	 String name; 		// 列名
	 String comments;	// 描述
	 String jdbcType;	// JDBC类型
	 String javaType;	// JAVA类型
	 String javaField;	// JAVA字段名
	 String isPk;		// 是否主键（1：主键）
	 String isNull;		// 是否可为空（1：可为空；0：不为空）
	 String isInsert;	// 是否为插入字段（1：插入字段）
	 String isEdit;		// 是否编辑字段（1：编辑字段）
	 String isList;		// 是否列表字段（1：列表字段）
	 String isQuery;		// 是否查询字段（1：查询字段）
	 String queryType;	// 查询方式（等于、不等于、大于、小于、范围、左LIKE、右LIKE、左右LIKE）
	 String showType;	// 字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）
	 String dictType;	// 字典类型
	 Integer sort;		// 排序（升序）
}