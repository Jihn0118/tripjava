import { ref } from "vue";
import { useRouter } from "vue-router";
import { defineStore } from "pinia";

export const useAttractionStore = defineStore(
    "attractionStore",
    () => {
        const router = useRouter();
 
        const attractionInfo = ref(null);
        
        return {
            attractionInfo,
        }
    }
)