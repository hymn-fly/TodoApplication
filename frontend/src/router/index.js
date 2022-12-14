import Vue from 'vue';
import VueRouter from 'vue-router';
import HomeView from '../views/HomeView.vue';

Vue.use(VueRouter);

const routes = [
	{
		path: '/',
		name: 'home',
		component: HomeView,
	},
	{
		path: '/login',
		name: 'login',
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () =>
			import(/* webpackChunkName: "about" */ '../components/Login.vue'),
	},
	{
		path: '/sign-up',
		name: 'signUp',
		component: () => import('../components/SignUp.vue'),
	},
];

const router = new VueRouter({
	routes,
});

export default router;
