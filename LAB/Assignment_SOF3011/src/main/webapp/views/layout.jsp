<%--
  Created by IntelliJ IDEA.
  User: manh0
  Date: 4/1/2022
  Time: 3:19 AM
  To change this template use File | Settings | File Templates.
--%>


    <%@include file="../common/taglib.jsp" %>
<html>
<head>
    <title>HNAM</title>
    <style>
        body {
            background-image: url('https://ap.poly.edu.vn/theme/student_v2/media//bg/bg-3.jpg');
        }
    </style>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
</head>
<body>
<div class="container p-0">
    <%--    Star_Header--%>
    <%@include file="../common/header.jsp"%>
<%--    <jsp:include page="${headerL}"></jsp:include>--%>
    <%--EndHeader--%>


    <%--Star_Body--%>
    <div class="row">
        <%--    Star_Body--%>
        <div class="col-12 col-sm-12 container">
            <jsp:include page="${view1}"></jsp:include>

                <jsp:include page="${view2}"></jsp:include>

        </div>
        <%--    End_Body--%>

    </div>

    <%--EndBody--%>
        <div>
                        <%@include file="../common/footer.jsp"%>
<%--            <jsp:include page="${footerL}"></jsp:include>--%>

        </div>
</div>
</body>
</html>
