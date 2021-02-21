<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.employee.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.employee.home.createOrEditLabel')">Create or edit a Employee</h2>
                <div>
                    <div class="form-group" v-if="employee.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="employee.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.employee.firstName')" for="employee-firstName">First Name</label>
                        <input type="text" class="form-control" name="firstName" id="employee-firstName"
                            :class="{'valid': !$v.employee.firstName.$invalid, 'invalid': $v.employee.firstName.$invalid }" v-model="$v.employee.firstName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.employee.lastName')" for="employee-lastName">Last Name</label>
                        <input type="text" class="form-control" name="lastName" id="employee-lastName"
                            :class="{'valid': !$v.employee.lastName.$invalid, 'invalid': $v.employee.lastName.$invalid }" v-model="$v.employee.lastName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.employee.email')" for="employee-email">Email</label>
                        <input type="text" class="form-control" name="email" id="employee-email"
                            :class="{'valid': !$v.employee.email.$invalid, 'invalid': $v.employee.email.$invalid }" v-model="$v.employee.email.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.employee.phoneNumber')" for="employee-phoneNumber">Phone Number</label>
                        <input type="text" class="form-control" name="phoneNumber" id="employee-phoneNumber"
                            :class="{'valid': !$v.employee.phoneNumber.$invalid, 'invalid': $v.employee.phoneNumber.$invalid }" v-model="$v.employee.phoneNumber.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.employee.hireDate')" for="employee-hireDate">Hire Date</label>
                        <div class="d-flex">
                            <input id="employee-hireDate" type="datetime-local" class="form-control" name="hireDate" :class="{'valid': !$v.employee.hireDate.$invalid, 'invalid': $v.employee.hireDate.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.employee.hireDate.$model)"
                            @change="updateInstantField('hireDate', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.employee.salary')" for="employee-salary">Salary</label>
                        <input type="number" class="form-control" name="salary" id="employee-salary"
                            :class="{'valid': !$v.employee.salary.$invalid, 'invalid': $v.employee.salary.$invalid }" v-model.number="$v.employee.salary.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.employee.commissionPct')" for="employee-commissionPct">Commission Pct</label>
                        <input type="number" class="form-control" name="commissionPct" id="employee-commissionPct"
                            :class="{'valid': !$v.employee.commissionPct.$invalid, 'invalid': $v.employee.commissionPct.$invalid }" v-model.number="$v.employee.commissionPct.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.employee.department')" for="employee-department">Department</label>
                        <select class="form-control" id="employee-department" name="department" v-model="employee.departmentId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="departmentOption.id" v-for="departmentOption in departments" :key="departmentOption.id">{{departmentOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.employee.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./employee-update.component.ts">
</script>
