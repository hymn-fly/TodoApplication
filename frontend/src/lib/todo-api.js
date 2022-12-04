import axios from 'axios';

const hostname = window && window.location && window.location.hostname;

let apiHost;

if (hostname === 'localhost') {
	apiHost = 'http://localhost:8080';
} else {
	apiHost = 'https://api.hymn-fly.com';
}

const apiService = axios.create({
	baseURL: apiHost,
	timeout: 1000,
});

const STORAGE_TOKEN_KEY = 'ACCESS_TOKEN';
const ROOT_URL = '/#/';
const LOGIN_URL = '/#/login';

apiService.interceptors.response.use(
	function (response) {
		return response;
	},
	function (error) {
		if (error.response.status === 403) {
			window.location.href = LOGIN_URL;
		}
		return Promise.reject(error);
	},
);

const getToken = function () {
	const token = sessionStorage.getItem(STORAGE_TOKEN_KEY);
	if (token && token !== null) {
		return `Bearer ${token}`;
	}
	return null;
};

const TodoApi = {
	GET: async function getTodoList() {
		const result = await apiService.get('/todo', {
			headers: { Authorization: getToken() },
		});
		return result.data;
	},

	POST: async function createTodo(todoTitle) {
		const result = await apiService.post(
			'/todo',
			{ title: todoTitle },
			{ headers: { Authorization: getToken() } },
		);
		return result.data;
	},

	DELETE: async function deleteTodo(itemId) {
		await apiService.delete(`/todo/${itemId}`, {
			headers: { Authorization: getToken() },
		});
	},

	SignIn: async function signin(userDto) {
		const res = await apiService.post('/signin', userDto);
		if (res.data.token) {
			sessionStorage.setItem(STORAGE_TOKEN_KEY, res.data.token);
			window.location.href = ROOT_URL;
		}
	},

	SignOut: function signOut() {
		sessionStorage.removeItem(STORAGE_TOKEN_KEY);
		window.location.href = LOGIN_URL;
	},

	SignUp: async function signUp(userDto) {
		await apiService.post('/signup', userDto);
		window.location.href = LOGIN_URL;
	},
};

export { TodoApi };
