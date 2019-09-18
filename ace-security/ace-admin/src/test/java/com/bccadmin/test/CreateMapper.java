package com.bccadmin.test;

public class CreateMapper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string="TASKDATAID,VVAPERS,VVASTATUS,VVAGROUP,VVAREPORT,VVATESTFN,VVAPLANRP,VVADOCABS,CLDGXHBG,VVATM,CTCFN,VVATCNFN,VVAPRS,VVARYSXH,VVARWSFN,VVASJCJXHBG,VVASJFXCLXHBG,VVASHQRBG,VVASJYZBG,VVAXHBG";
		String[] fields=string.split(",");
		for(String s:fields) {
			System.out.println("<result column=\""+s+"\" jdbcType=\"VARCHAR\" property=\""+s.toLowerCase()+"\"/>");
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
		for(String s:fields) {
			System.out.print("#{"+s.toLowerCase()+",jdbcType=VARCHAR},");
			
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
		for(String s:fields) {
			System.out.println("<if test=\""+s.toLowerCase()+" != null\">");
			System.out.println(s);
			System.out.println("</if>");
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
		for(String s:fields) {
			System.out.println("<if test=\""+s.toLowerCase()+" != null\">");
			System.out.println("#{"+s.toLowerCase()+",jdbcType=VARCHAR},");
			System.out.println("</if>");
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
		for(String s:fields) {
			System.out.println("<if test=\""+s.toLowerCase()+" != null\">");
			System.out.println(s+" = #{"+s.toLowerCase()+",jdbcType=VARCHAR},");
			System.out.println("</if>");
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
		for(String s:fields) {
			System.out.println( s+" = #{"+s.toLowerCase()+",jdbcType=VARCHAR},");
		}
	}

}
