<%@page import="com.db.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Page</title>
<%@include file="component/allcss.jsp"%>

<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>

</head>
<body>
	<%@include file="component/navbar.jsp"%>



	<div id="carouselExampleIndicators" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="0" class="active" aria-current="true"
				aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="img/OIP_0.jpg" class="d-block w-100" alt="..."
					height="700px">
			</div>
			<div class="carousel-item">
				<img src="img/hinh.jpg" class="d-block w-100" alt="..."
					height="700px">
			</div>
			<div class="carousel-item">
				<img src="img/hinh2	.jpg" class="d-block w-100" alt="..."
					height="700px">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>

	<div class="container p-3">
		<p class="text-center fs-2">Tầm Nhìn - Sứ Mệnh</p>

		<div class="row">
			<div class="col-md-8 p-5">
				<div class="row">
					<div class="col-md-6">
						<div class="card paint-card">
							<div class="card-body">
								<p class="fs-5">Hiệu quả điều trị cao - Thành tựu nổi bật</p>
								<p>Ứng dụng kỹ thuật tiên tiến, phác đồ chuyên biệt, tăng tỷ
									lệ thành công.</p>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="card paint-card">
							<div class="card-body">
								<p class="fs-5">Quy trình khoa học - Toàn diện</p>
								<p>Quy trình khám, tư vấn và điều trị toàn diện, phối hợp
									chặt chẽ giữa các chuyên khoa.</p>
							</div>
						</div>
					</div>
					<div class="col-md-6 mt-2">
						<div class="card paint-card">
							<div class="card-body">
								<p class="fs-5">Chuyên gia - bác sĩ giỏi</p>
								<p>Quy tụ đội ngũ chuyên gia đầu ngành, bác sĩ chuyên môn
									cao, giàu kinh nghiệm.</p>
							</div>
						</div>
					</div>
					<div class="col-md-6 mt-2">
						<div class="card paint-card">
							<div class="card-body">
								<p class="fs-5"> Dịch vụ cao cấp - Chi phí hợp lý</p>
								<p>Đội ngũ chăm sóc khách hàng chuyên nghiệp, tư vấn miễn
									phí qua tổng đài, website và fanpage.</p>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<img alt="" src="img/img0.jpg">
			</div>

		</div>
	</div>

	<hr>

	<div class="container p-2">
		<p class="text-center fs-2">Our Team</p>

		<div class="row">
			<div class="col-md 3">
				<div class="card point-card">
					<div class="card-body text-center">
						<img src="img/OIP_4.jpg" width="200px" height="300px">
						<p class="fw-bold fs-5">Nguyễn Thị Linh</p>
						<p class="fs-7">(Doctor)</p>
					</div>
				</div>
			</div>

			<div class="col-md 3">
				<div class="card point-card">
					<div class="card-body text-center">
						<img src="img/OIP_5.jpg" width="250px" height="300px">
						<p class="fw-bold fs-5">Nguyễn Hoa</p>
						<p class="fs-7">(CEO & Doctor)</p>
					</div>
				</div>
			</div>

			<div class="col-md 3">
				<div class="card point-card">
					<div class="card-body text-center">
						<img src="img/bacsinam.jpg" width="250px" height="300px">
						<p class="fw-bold fs-5">Trần Hoàng Nam</p>
						<p class="fs-7">(Doctor)</p>
					</div>
				</div>
			</div>

			<div class="col-md 3">
				<div class="card point-card">
					<div class="card-body text-center">
						<img src="img/OIP_6.jpg" width="200px" height="300px">
						<p class="fw-bold fs-5">Võ Triệu Đạt</p>
						<p class="fs-7">(Doctor)</p>
					</div>
				</div>
			</div>

		</div>
	</div>

	<%@include file="component/footer.jsp"%>

</body>
</html>