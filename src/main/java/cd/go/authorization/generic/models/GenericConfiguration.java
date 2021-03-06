/*
 * Copyright 2017 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.authorization.generic.models;

import cd.go.authorization.generic.GenericApiClient;
import cd.go.authorization.generic.annotation.ProfileField;
import cd.go.authorization.generic.annotation.Validatable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

import static cd.go.authorization.generic.utils.Util.GSON;

public class GenericConfiguration implements Validatable {

    @Expose
    @SerializedName("GenericEndpoint")
    @ProfileField(key = "GenericEndpoint", required = true, secure = false)
    private String genericEndpoint;

    @Expose
    @SerializedName("ClientId")
    @ProfileField(key = "ClientId", required = true, secure = true)
    private String clientId;

    @Expose
    @SerializedName("ClientSecret")
    @ProfileField(key = "ClientSecret", required = true, secure = true)
    private String clientSecret;

    private GenericApiClient genericApiClient;

    public GenericConfiguration() {
    }

    public GenericConfiguration(String genericEndpoint, String clientId, String clientSecret) {
        this.genericEndpoint = genericEndpoint;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String genericEndpoint() {
        return genericEndpoint;
    }

    public String clientId() {
        return clientId;
    }

    public String clientSecret() {
        return clientSecret;
    }

    public String toJSON() {
        return GSON.toJson(this);
    }

    public static GenericConfiguration fromJSON(String json) {
        return GSON.fromJson(json, GenericConfiguration.class);
    }

    public Map<String, String> toProperties() {
        return GSON.fromJson(toJSON(), new TypeToken<Map<String, String>>() {
        }.getType());
    }

    public GenericApiClient genericApiClient() {
        if (genericApiClient == null) {
            genericApiClient = new GenericApiClient(this);
        }

        return genericApiClient;
    }
}
