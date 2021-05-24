<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<h1>리스트</h1>
	<div>
	<form action="list?page=${param.page}" method="get">	
		<input type="text" name="search">
		<input type="submit" value="Title검색">
	</form>
		<table>
			<c:forEach var="vo" items="${list}" varStatus="status">
				<c:if test="${status.first}">
					<tr id="tHead">
						<th>NO</th>
						<th>TITLE</th>
						<th>CTNT</th>
						<th>WRITTER</th>
						<th>DATE</th>
						<th>LIKE</th>
					</tr>
				</c:if>
				<tr class="ctr" onclick="search_result(${vo.title})">
					<td>${vo.iboard}</td>
					<td id="title" >${vo.title}</td>	
					<td>${vo.ctnt}</td>
					<td>${vo.writerNm}</td>
					<td>${vo.regdt}</td>
				</tr>
			</c:forEach>
		</table>
		<div><c:forEach begin="1" end="${requestScope.totalPage}" var="cnt">
			<a href="list?page=${cnt}&search=${param.search}"><span>${cnt}</span></a>
		</c:forEach></div>
	</div>	
	<script>
		function togodetail(iboard) {
			location.href="detail?iboard="+iboard;
		}
		/*function search_result(title){
			var str=title;
			console.log(str);
			console.log(str.replace(/${param.search}/g,red(${param.search})));
		}
		function red(title){
			return title="<span style='background-color:red'>"+title+"</span>";
		}*/
	</script>
