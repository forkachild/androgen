package com.androgen.template.data.model.error

class PermissionNotGrantedError(permission: String) : Exception("Permission $permission not granted")