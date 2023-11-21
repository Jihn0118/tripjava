import {localAxios} from "@/util/http-commons";

const local = localAxios();

function listComment(contentId, success, fail) {
    local.get(`/comment/${contentId}`).then(success).catch(fail);
}

function enrollComment(param, success, fail) {
    local.post(`/comment`, param).then(success).catch(fail);
}

function deleteCommentById(commentId, success, fail) {
    local.delete(`/comment/${commentId}`).then(success).catch(fail);
}

export {
    listComment, enrollComment, deleteCommentById
};