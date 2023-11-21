import {localAxios} from "@/util/http-commons";

const local = localAxios();

function getHeartStatement(param, success, fail) {
    local.get("/heart/heartState", {params: param}).then(success).catch(fail);
}

function doHeart(param, success, fail) {
    local.post("/heart", param).then(success).catch(fail);
}

function cancelHeart(param, success, fail) {
    local.delete("/heart", param).then(success).catch(fail);
}

export {
    getHeartStatement, doHeart, cancelHeart
};