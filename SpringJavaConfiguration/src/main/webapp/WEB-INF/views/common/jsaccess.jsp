<%-- JS Access path --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/lib/jquery/1.11.2/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
    window.App = {
        CP: "${cp}".replace(/\/+$/, "/").replace(/:80\//, "/"),
        SCP: "${scp}".replace(/\/+$/, "/").replace(/:80\//, "/"),
        UCP: "${ucp}".replace(/\/+$/, "/").replace(/:80\//, "/")
    };

</script>

