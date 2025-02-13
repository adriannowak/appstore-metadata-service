/*
 * If not stated otherwise in this file or this component's LICENSE file the
 * following copyright and licenses apply:
 *
 * Copyright 2022 Liberty Global Technology Services BV
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lgi.appstore.metadata.util;

import com.lgi.appstore.metadata.model.ApplicationType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public final class ApplicationTypeHelper {
    private ApplicationTypeHelper() {
    }

    public static boolean isSupported(String applicationType) {
        return fromValue(applicationType).isPresent();
    }

    public static boolean isNativeApplication(String applicationType, Collection<ApplicationType> webApplications) {
        return fromValue(applicationType).map(e -> !webApplications.contains(e)).orElse(false);
    }

    public static boolean isWebApplication(String applicationType, Collection<ApplicationType> webApplications) {
        return fromValue(applicationType).map(webApplications::contains).orElse(false);
    }

    private static Optional<ApplicationType> fromValue(String applicationType) {
        if (applicationType == null) {
            return Optional.empty();
        }
        return Arrays.stream(ApplicationType.values())
                .filter(e -> applicationType.equalsIgnoreCase(e.getValue()))
                .findFirst();
    }

}