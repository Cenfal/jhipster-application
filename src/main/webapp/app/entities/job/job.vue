<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhipsterApplicationApp.job.home.title')" id="job-heading">Jobs</span>
            <router-link :to="{name: 'JobCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-job">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhipsterApplicationApp.job.home.createLabel')">
                    Create a new Job
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && jobs && jobs.length === 0">
            <span v-text="$t('jhipsterApplicationApp.job.home.notFound')">No jobs found</span>
        </div>
        <div class="table-responsive" v-if="jobs && jobs.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.job.jobTitle')">Job Title</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.job.minSalary')">Min Salary</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.job.maxSalary')">Max Salary</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.job.task')">Task</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.job.employee')">Employee</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="job in jobs"
                    :key="job.id">
                    <td>
                        <router-link :to="{name: 'JobView', params: {jobId: job.id}}">{{job.id}}</router-link>
                    </td>
                    <td>{{job.jobTitle}}</td>
                    <td>{{job.minSalary}}</td>
                    <td>{{job.maxSalary}}</td>
                    <td>
                        <span v-for="(task, i) in job.tasks" :key="task.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'TaskView', params: {taskId: task.id}}">{{task.title}}</router-link>
                        </span>
                    </td>
                    <td>
                        <div v-if="job.employeeId">
                            <router-link :to="{name: 'EmployeeView', params: {employeeId: job.employeeId}}">{{job.employeeId}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'JobView', params: {jobId: job.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'JobEdit', params: {jobId: job.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(job)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="jhipsterApplicationApp.job.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-job-heading" v-text="$t('jhipsterApplicationApp.job.delete.question', {'id': removeId})">Are you sure you want to delete this Job?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-job" v-text="$t('entity.action.delete')" v-on:click="removeJob()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./job.component.ts">
</script>
