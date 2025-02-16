
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Bookstore</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
    .navbar {
        background-color: #87CEEB !important; /* Light Sky Blue */
    }
    .navbar-brand {
        font-weight: bold;
    }
</style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light">
  <a class="navbar-brand" href="#">Online Bookstore</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
 
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item"><a class="nav-link" href="/index">Home</a></li>
      <li class="nav-item"><a class="nav-link" href="/manage/books/Allbooks">Books</a></li>
      <li class="nav-item"><a class="nav-link" href="/cart">Cart</a></li>
      <li class="nav-item"><a class="nav-link" href="/checkout">Checkout</a></li>

      <!-- Dropdown for Account -->
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="accountDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Account
        </a>
        <div class="dropdown-menu" aria-labelledby="accountDropdown">
          <a class="dropdown-item" href="/admin/dashboard">Admin</a>
          <a class="dropdown-item" href="Registration_Page">User</a>
        </div>
      </li>
    </ul>

    <!-- Search Bar -->
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search Books" aria-label="Search">
      <button class="btn btn-primary" type="submit">Search</button>
    </form>
  </div>
</nav>

</body>
</html>
