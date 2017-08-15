class GenTable{
	 String name; 	// 名称
	 String comments;		// 描述
	 String className;		// 实体类名称
	 String parentTable;		// 关联父表
	 String parentTableFk;		// 关联父表外键
	 List<GenTableColumn> columnList  ;	// 表列
	 String nameLike; 	// 按名称模糊查询
	 List<String> pkList; // 当前表主键列表
	 GenTable parent;	// 父表对象
	 List<GenTable> childList ;	// 子表列表
}