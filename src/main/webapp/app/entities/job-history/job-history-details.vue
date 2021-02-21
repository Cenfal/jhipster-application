<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="jobHistory">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.jobHistory.detail.title')">JobHistory</span> {{jobHistory.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.jobHistory.startDate')">Start Date</span>
                    </dt>
                    <dd>
                        <span v-if="jobHistory.startDate">{{$d(Date.parse(jobHistory.startDate), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.jobHistory.endDate')">End Date</span>
                    </dt>
                    <dd>
                        <span v-if="jobHistory.endDate">{{$d(Date.parse(jobHistory.endDate), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.jobHistory.language')">Language</span>
                    </dt>
                    <dd>
                        <span v-text="$t('jhipsterApplicationApp.Language.' + jobHistory.language)">{{jobHistory.language}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.jobHistory.job')">Job</span>
                    </dt>
                    <dd>
                        <div v-if="jobHistory.jobId">
                            <router-link :to="{name: 'JobView', params: {jobId: jobHistory.jobId}}">{{jobHistory.jobId}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.jobHistory.department')">Department</span>
                    </dt>
                    <dd>
                        <div v-if="jobHistory.departmentId">
                            <router-link :to="{name: 'DepartmentView', params: {departmentId: jobHistory.departmentId}}">{{jobHistory.departmentId}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.jobHistory.employee')">Employee</span>
                    </dt>
                    <dd>
                        <div v-if="jobHistory.employeeId">
                            <router-link :to="{name: 'EmployeeView', params: {employeeId: jobHistory.employeeId}}">{{jobHistory.employeeId}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="jobHistory.id" :to="{name: 'JobHistoryEdit', params: {jobHistoryId: jobHistory.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./job-history-details.component.ts">
</script>
