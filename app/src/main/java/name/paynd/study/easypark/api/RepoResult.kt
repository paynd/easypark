package name.paynd.study.easypark.api

sealed class RepoResult {
    data class Success(val cities: List<CityDistance>) : RepoResult()
    data class Error(val msg: String) : RepoResult()
}