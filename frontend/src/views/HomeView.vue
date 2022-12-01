<template>
	<v-card class="mx-auto" max-width="900">
		<todo-input-vue v-on:addItem="addItem"></todo-input-vue>
		<todo-list-vue v-bind:items="items" />
	</v-card>
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
		};
	},

	mounted() {
		TodoApi.GET().then(result => {
			this.items = result;
		});
	},

	methods: {
		addItem(title) {
			TodoApi.POST(title).then(result => this.items.push(result));
		},
	},
};
</script>
