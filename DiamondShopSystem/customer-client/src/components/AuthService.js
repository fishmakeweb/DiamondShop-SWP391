import axios from "axios";

class AuthService {
  static BASE_URL = "http://localhost:8080/api/auth";
  static USER_ID = null;
  static async login(username, password) {
    try {
      const response = await axios.post(`${this.BASE_URL}/login`, {
        username,
        password,
      });
      const { token, refreshToken, staff, customer } = response.data;
      this.USER_ID = customer ? customer.userId : null; // Store userId in the static variable
      if (token != null) {
        localStorage.setItem("token", token);
        localStorage.setItem("refreshToken", refreshToken);
      }
      if (staff) {
        localStorage.setItem("role", "STAFF");
        localStorage.setItem("user", JSON.stringify(staff));
      } else if (customer) {
        localStorage.setItem("role", "CUSTOMER");
        localStorage.setItem("user", JSON.stringify(customer));
      }
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  static getUserId() {
    return this.USER_ID;
  }

  static async registerCustomer(userData) {
    try {
      const response = await axios.post(
        `${this.BASE_URL}/register/customer`,
        userData
      );
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  static async registerStaff(userData) {
    try {
      const response = await axios.post(
        `${this.BASE_URL}/register/staff`,
        userData
      );
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  static async getProfile(token) {
    try {
      const response = await axios.get(`http://localhost:8080/api/profile`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  static async refreshToken() {
    try {
      const refreshToken = localStorage.getItem("refreshToken");
      const response = await axios.post(`${this.BASE_URL}/refresh`, {
        refreshToken,
      });
      localStorage.setItem("token", response.data.token);
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  static logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("refreshToken");
    localStorage.removeItem("role");
    localStorage.removeItem("user");
  }

  static isAuthenticated() {
    const token = localStorage.getItem("token");
    return !!token;
  }

  static isStaff() {
    const role = localStorage.getItem("role");
    return role === "STAFF";
  }

  static isCustomer() {
    const role = localStorage.getItem("role");
    return role === "CUSTOMER";
  }
}

export default AuthService;
