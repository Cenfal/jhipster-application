<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhipsterApplicationApp.jobHistory.home.title')" id="job-history-heading">Job Histories</span>
            <router-link :to="{name: 'JobHistoryCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-job-history">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhipsterApplicationApp.jobHistory.home.createLabel')">
                    Create a new Job History
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
        <div class="alert alert-warning" v-if="!isFetching && jobHistories && jobHistories.length === 0">
            <span v-text="$t('jhipsterApplicationApp.jobHistory.home.notFound')">No jobHistories found</span>
        </div>
        <div class="table-responsive" v-if="jobHistories && jobHistories.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('startDate')"><span v-text="$t('jhipsterApplicationApp.jobHistory.startDate')">Start Date</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'startDate'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('endDate')"><span v-text="$t('jhipsterApplicationApp.jobHistory.endDate')">End Date</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'endDate'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('language')"><span v-text="$t('jhipsterApplicationApp.jobHistory.language')">Language</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'language'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('jobId')"><span v-text="$t('jhipsterApplicationApp.jobHistory.job')">Job</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'jobId'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('departmentId')"><span v-text="$t('jhipsterApplicationApp.jobHistory.department')">Department</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'departmentId'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('employeeId')"><span v-text="$t('jhipsterApplicationApp.jobHistory.employee')">Employee</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'employeeId'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="jobHistory in jobHistories"
                    :key="jobHistory.id">
                    <td>
                        <router-link :to="{name: 'JobHistoryView', params: {jobHistoryId: jobHistory.id}}">{{jobHistory.id}}</router-link>
                    </td>
                    <td>{{jobHistory.startDate ? $d(Date.parse(jobHistory.startDate), 'short') : ''}}</td>
                    <td>{{jobHistory.endDate ? $d(Date.parse(jobHistory.endDate), 'short') : ''}}</td>
                    <td v-text="$t('jhipsterApplicationApp.Language.' + jobHistory.language)">{{jobHistory.language}}</td>
                    <td>
                        <div v-if="jobHistory.jobId">
                            <router-link :to="{name: 'JobView', params: {jobId: jobHistory.jobId}}">{{jobHistory.jobId}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="jobHistory.departmentId">
                            <router-link :to="{name: 'DepartmentView', params: {departmentId: jobHistory.departmentId}}">{{jobHistory.departmentId}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="jobHistory.employeeId">
                            <router-link :to="{name: 'EmployeeView', params: {employeeId: jobHistory.employeeId}}">{{jobHistory.employeeId}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'JobHistoryView', params: {jobHistoryId: jobHistory.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'JobHistoryEdit', params: {jobHistoryId: jobHistory.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(jobHistory)"
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
                <infinite-loading
                    ref="infiniteLoading"
                    v-if="totalItems > itemsPerPage"
                    :identifier="infiniteId"
                    slot="append"
                    @infinite="loadMore"
                    force-use-infinite-wrapper=".el-table__body-wrapper"
                    :distance='20'>
                </infinite-loading>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="jhipsterApplicationApp.jobHistory.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-jobHistory-heading" v-text="$t('jhipsterApplicationApp.jobHistory.delete.question', {'id': removeId})">Are you sure you want to delete this Job History?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-jobHistory" v-text="$t('entity.action.delete')" v-on:click="removeJobHistory()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./job-history.component.ts">
</script>
