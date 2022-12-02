<template>
	<h1 v-if="loading">로딩중 ...</h1>
	<v-container v-else>
		<v-app-bar color="deep-purple accent-4" dark elevation="10">
			<v-toolbar-title>Get the work done</v-toolbar-title>
			<v-spacer></v-spacer>
			<v-btn @click="signOut">SIGN OUT</v-btn>
		</v-app-bar>
		<v-card class="mx-auto" max-width="900">
			<todo-input-vue v-on:addItem="addItem"></todo-input-vue>
			<todo-list-vue v-bind:items="items" />
		</v-card>
	</v-container>
</template>

<script>
import TodoInputVue from '@/components/TodoInput.vue';
import TodoListVue from '@/components/TodoList.vue';
import { TodoApi } from '@/lib/todo-api';

export default {
	name: 'HomeView',
	components: {
		TodoInputVue,
		TodoListVue,
	},

	data() {
		return {
			items: [],
			loading: true,
		};
	},

	mounted() {
		TodoApi.GET().then(result => {
			this.items = result;
		});
		this.loading = false;
	},

	methods: {
		addItem(title) {
			TodoApi.POST(title).then(result => this.items.push(result));
		},

		signOut() {
			TodoApi.SignOut();
		},
	},
};
</script>
