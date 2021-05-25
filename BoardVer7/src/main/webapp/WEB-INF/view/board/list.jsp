<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>리스트</h1>
<div>
	<form action="list?page=${param.page}" method="get">
		<div>
			<select name="searchType">
				<option value="1" ${param.searchType ==1 ?'selected':''}>제목+내용</option>
				<option value="2" ${param.searchType ==2 ?'selected':''}>제목</option>
				<option value="3" ${param.searchType ==3 ?'selected':''}>내용</option>
				<option value="4" ${param.searchType ==4 ?'selected':''}>글쓴이</option>
			</select> <input type="text" name="searchText" value="${param.searchText}">
			<input type="submit" value="검색">
		</div>

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
			<tr class="ctr" onclick="togodetail(${vo.iboard})">
				<td>${vo.iboard}</td>
				<td><c:choose>
						<c:when test="${param.searchType eq 1||param.searchType eq 2}">
							${vo.title.replace(param.searchText,'<mark>'+=param.searchText+='</mark>')}
						</c:when>
						<c:otherwise>
						${vo.title}
						</c:otherwise>
					</c:choose>
				</td>
				<td><c:choose>
						<c:when test="${param.searchType eq 1||param.searchType eq 3}">
							${vo.ctnt.replace(param.searchText,'<mark>'+=param.searchText+='</mark>')}
						</c:when>
						<c:otherwise>
						${vo.ctnt}
						</c:otherwise>
					</c:choose>
				</td>
				<td><c:choose>
						<c:when test="${param.searchType eq 4 }">
							${vo.writerNm.replace(param.searchText,'<mark>'+=param.searchText+='</mark>')}
						</c:when>
						<c:otherwise>
						${vo.writerNm}
						</c:otherwise>
					</c:choose></td>
				<td>${vo.regdt}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<c:forEach begin="1" end="${requestScope.totalPage}" var="cnt">
			<a
				href="list?page=${cnt}&searchText=${param.searchText}&searchType=${param.searchType}"><span>${cnt}</span></a>
		</c:forEach>
	</div>
</div>
<script>
		function togodetail(iboard) {
			location.href="detail?iboard="+iboard;
		}
</script>
