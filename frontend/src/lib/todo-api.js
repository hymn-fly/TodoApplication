import axios from 'axios';

const apiHost = 'http://localhost:8080';

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
		apiService.post('/signin', userDto).then(response => {
			if (response.data.token) {
				sessionStorage.setItem(STORAGE_TOKEN_KEY, response.data.token);
				window.location.href = ROOT_URL;
			}
		});
	},

	SignOut: function signOut() {
		sessionStorage.removeItem(STORAGE_TOKEN_KEY);
		window.location.href = LOGIN_URL;
	},
};

export { TodoApi };
