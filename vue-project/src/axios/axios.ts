import axios, { AxiosInstance } from "axios";

// http request 欄截
const apiClient: AxiosInstance = axios.create({
  baseURL: "/api",
  headers: {
    'Content-type': 'application/json; charset=utf-8',
    "Access-Control-Allow-Origin": process.env.API_ROOT,
    "Access-Control-Allow-Methods": "GET, PUT, POST, DELETE, OPTIONS",
    "Access-Control-Max-Age": "86400",
  },
});

export const getProductList = (): Promise<any> => apiClient.get("/product");
export const getProduct = (productId: number): Promise<any> => apiClient.get(`/product/${productId}`);
export const updateProduct = (product: unknown): Promise<any> => apiClient.put("/product/update", product);
export const addProduct = (product: unknown): Promise<any> => apiClient.post("/product/insert", product);
export const deleteProduct = (products: unknown): Promise<any> => apiClient.delete("/product/delete", { data: products });

export const getUserData = (id: number): Promise<any> => apiClient.get(`/user/${id}`);
export const checkLogin = (): Promise<any> => apiClient.get(`/user/isLogin`);

// http request 欄截
const loginApiClient: AxiosInstance = axios.create({
  baseURL: "/api",
  headers: {
    'Content-type': 'multipart/form-data; charset=utf-8',
    "Access-Control-Allow-Origin": process.env.API_ROOT,
    "Access-Control-Allow-Methods": "GET, PUT, POST, DELETE, OPTIONS",
    "Access-Control-Max-Age": "86400",
  },
});

export const login = (formData: FormData): Promise<any> => loginApiClient.post("/login", formData)  ;
