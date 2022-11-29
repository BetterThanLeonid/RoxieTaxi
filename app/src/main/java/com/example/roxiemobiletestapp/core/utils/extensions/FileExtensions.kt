/*
 * Created by Nedushny at 29/11/2022.
 */

package com.example.roxiemobiletestapp.core.utils.extensions

import java.io.File

val File.isFileExists: Boolean
    get() = this.isFile && !this.isDirectory