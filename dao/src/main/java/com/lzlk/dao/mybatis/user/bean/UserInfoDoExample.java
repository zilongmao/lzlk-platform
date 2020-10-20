package com.lzlk.dao.mybatis.user.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserInfoDoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public UserInfoDoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeIsNull() {
            addCriterion("last_modify_time is null");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeIsNotNull() {
            addCriterion("last_modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeEqualTo(Date value) {
            addCriterion("last_modify_time =", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeNotEqualTo(Date value) {
            addCriterion("last_modify_time <>", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeGreaterThan(Date value) {
            addCriterion("last_modify_time >", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_modify_time >=", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeLessThan(Date value) {
            addCriterion("last_modify_time <", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_modify_time <=", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeIn(List<Date> values) {
            addCriterion("last_modify_time in", values, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeNotIn(List<Date> values) {
            addCriterion("last_modify_time not in", values, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeBetween(Date value1, Date value2) {
            addCriterion("last_modify_time between", value1, value2, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_modify_time not between", value1, value2, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Boolean value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Boolean value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Boolean value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Boolean value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Boolean> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Boolean> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdIsNull() {
            addCriterion("official_account_open_id is null");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdIsNotNull() {
            addCriterion("official_account_open_id is not null");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdEqualTo(String value) {
            addCriterion("official_account_open_id =", value, "officialAccountOpenId");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdNotEqualTo(String value) {
            addCriterion("official_account_open_id <>", value, "officialAccountOpenId");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdGreaterThan(String value) {
            addCriterion("official_account_open_id >", value, "officialAccountOpenId");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("official_account_open_id >=", value, "officialAccountOpenId");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdLessThan(String value) {
            addCriterion("official_account_open_id <", value, "officialAccountOpenId");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdLessThanOrEqualTo(String value) {
            addCriterion("official_account_open_id <=", value, "officialAccountOpenId");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdLike(String value) {
            addCriterion("official_account_open_id like", value, "officialAccountOpenId");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdNotLike(String value) {
            addCriterion("official_account_open_id not like", value, "officialAccountOpenId");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdIn(List<String> values) {
            addCriterion("official_account_open_id in", values, "officialAccountOpenId");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdNotIn(List<String> values) {
            addCriterion("official_account_open_id not in", values, "officialAccountOpenId");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdBetween(String value1, String value2) {
            addCriterion("official_account_open_id between", value1, value2, "officialAccountOpenId");
            return (Criteria) this;
        }

        public Criteria andOfficialAccountOpenIdNotBetween(String value1, String value2) {
            addCriterion("official_account_open_id not between", value1, value2, "officialAccountOpenId");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdIsNull() {
            addCriterion("applet_open_id is null");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdIsNotNull() {
            addCriterion("applet_open_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdEqualTo(String value) {
            addCriterion("applet_open_id =", value, "appletOpenId");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdNotEqualTo(String value) {
            addCriterion("applet_open_id <>", value, "appletOpenId");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdGreaterThan(String value) {
            addCriterion("applet_open_id >", value, "appletOpenId");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("applet_open_id >=", value, "appletOpenId");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdLessThan(String value) {
            addCriterion("applet_open_id <", value, "appletOpenId");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdLessThanOrEqualTo(String value) {
            addCriterion("applet_open_id <=", value, "appletOpenId");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdLike(String value) {
            addCriterion("applet_open_id like", value, "appletOpenId");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdNotLike(String value) {
            addCriterion("applet_open_id not like", value, "appletOpenId");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdIn(List<String> values) {
            addCriterion("applet_open_id in", values, "appletOpenId");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdNotIn(List<String> values) {
            addCriterion("applet_open_id not in", values, "appletOpenId");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdBetween(String value1, String value2) {
            addCriterion("applet_open_id between", value1, value2, "appletOpenId");
            return (Criteria) this;
        }

        public Criteria andAppletOpenIdNotBetween(String value1, String value2) {
            addCriterion("applet_open_id not between", value1, value2, "appletOpenId");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdIsNull() {
            addCriterion("we_chat_union_id is null");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdIsNotNull() {
            addCriterion("we_chat_union_id is not null");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdEqualTo(String value) {
            addCriterion("we_chat_union_id =", value, "weChatUnionId");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdNotEqualTo(String value) {
            addCriterion("we_chat_union_id <>", value, "weChatUnionId");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdGreaterThan(String value) {
            addCriterion("we_chat_union_id >", value, "weChatUnionId");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdGreaterThanOrEqualTo(String value) {
            addCriterion("we_chat_union_id >=", value, "weChatUnionId");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdLessThan(String value) {
            addCriterion("we_chat_union_id <", value, "weChatUnionId");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdLessThanOrEqualTo(String value) {
            addCriterion("we_chat_union_id <=", value, "weChatUnionId");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdLike(String value) {
            addCriterion("we_chat_union_id like", value, "weChatUnionId");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdNotLike(String value) {
            addCriterion("we_chat_union_id not like", value, "weChatUnionId");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdIn(List<String> values) {
            addCriterion("we_chat_union_id in", values, "weChatUnionId");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdNotIn(List<String> values) {
            addCriterion("we_chat_union_id not in", values, "weChatUnionId");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdBetween(String value1, String value2) {
            addCriterion("we_chat_union_id between", value1, value2, "weChatUnionId");
            return (Criteria) this;
        }

        public Criteria andWeChatUnionIdNotBetween(String value1, String value2) {
            addCriterion("we_chat_union_id not between", value1, value2, "weChatUnionId");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Integer value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Integer value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Integer value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Integer value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Integer value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Integer> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Integer value1, Integer value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Integer value1, Integer value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxIsNull() {
            addCriterion("head_image_max is null");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxIsNotNull() {
            addCriterion("head_image_max is not null");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxEqualTo(String value) {
            addCriterion("head_image_max =", value, "headImageMax");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxNotEqualTo(String value) {
            addCriterion("head_image_max <>", value, "headImageMax");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxGreaterThan(String value) {
            addCriterion("head_image_max >", value, "headImageMax");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxGreaterThanOrEqualTo(String value) {
            addCriterion("head_image_max >=", value, "headImageMax");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxLessThan(String value) {
            addCriterion("head_image_max <", value, "headImageMax");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxLessThanOrEqualTo(String value) {
            addCriterion("head_image_max <=", value, "headImageMax");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxLike(String value) {
            addCriterion("head_image_max like", value, "headImageMax");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxNotLike(String value) {
            addCriterion("head_image_max not like", value, "headImageMax");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxIn(List<String> values) {
            addCriterion("head_image_max in", values, "headImageMax");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxNotIn(List<String> values) {
            addCriterion("head_image_max not in", values, "headImageMax");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxBetween(String value1, String value2) {
            addCriterion("head_image_max between", value1, value2, "headImageMax");
            return (Criteria) this;
        }

        public Criteria andHeadImageMaxNotBetween(String value1, String value2) {
            addCriterion("head_image_max not between", value1, value2, "headImageMax");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinIsNull() {
            addCriterion("head_image_min is null");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinIsNotNull() {
            addCriterion("head_image_min is not null");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinEqualTo(String value) {
            addCriterion("head_image_min =", value, "headImageMin");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinNotEqualTo(String value) {
            addCriterion("head_image_min <>", value, "headImageMin");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinGreaterThan(String value) {
            addCriterion("head_image_min >", value, "headImageMin");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinGreaterThanOrEqualTo(String value) {
            addCriterion("head_image_min >=", value, "headImageMin");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinLessThan(String value) {
            addCriterion("head_image_min <", value, "headImageMin");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinLessThanOrEqualTo(String value) {
            addCriterion("head_image_min <=", value, "headImageMin");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinLike(String value) {
            addCriterion("head_image_min like", value, "headImageMin");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinNotLike(String value) {
            addCriterion("head_image_min not like", value, "headImageMin");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinIn(List<String> values) {
            addCriterion("head_image_min in", values, "headImageMin");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinNotIn(List<String> values) {
            addCriterion("head_image_min not in", values, "headImageMin");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinBetween(String value1, String value2) {
            addCriterion("head_image_min between", value1, value2, "headImageMin");
            return (Criteria) this;
        }

        public Criteria andHeadImageMinNotBetween(String value1, String value2) {
            addCriterion("head_image_min not between", value1, value2, "headImageMin");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNull() {
            addCriterion("phone_number is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNotNull() {
            addCriterion("phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberEqualTo(Long value) {
            addCriterion("phone_number =", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotEqualTo(Long value) {
            addCriterion("phone_number <>", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThan(Long value) {
            addCriterion("phone_number >", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("phone_number >=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThan(Long value) {
            addCriterion("phone_number <", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThanOrEqualTo(Long value) {
            addCriterion("phone_number <=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIn(List<Long> values) {
            addCriterion("phone_number in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotIn(List<Long> values) {
            addCriterion("phone_number not in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberBetween(Long value1, Long value2) {
            addCriterion("phone_number between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotBetween(Long value1, Long value2) {
            addCriterion("phone_number not between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andOther1IsNull() {
            addCriterion("other1 is null");
            return (Criteria) this;
        }

        public Criteria andOther1IsNotNull() {
            addCriterion("other1 is not null");
            return (Criteria) this;
        }

        public Criteria andOther1EqualTo(Integer value) {
            addCriterion("other1 =", value, "other1");
            return (Criteria) this;
        }

        public Criteria andOther1NotEqualTo(Integer value) {
            addCriterion("other1 <>", value, "other1");
            return (Criteria) this;
        }

        public Criteria andOther1GreaterThan(Integer value) {
            addCriterion("other1 >", value, "other1");
            return (Criteria) this;
        }

        public Criteria andOther1GreaterThanOrEqualTo(Integer value) {
            addCriterion("other1 >=", value, "other1");
            return (Criteria) this;
        }

        public Criteria andOther1LessThan(Integer value) {
            addCriterion("other1 <", value, "other1");
            return (Criteria) this;
        }

        public Criteria andOther1LessThanOrEqualTo(Integer value) {
            addCriterion("other1 <=", value, "other1");
            return (Criteria) this;
        }

        public Criteria andOther1In(List<Integer> values) {
            addCriterion("other1 in", values, "other1");
            return (Criteria) this;
        }

        public Criteria andOther1NotIn(List<Integer> values) {
            addCriterion("other1 not in", values, "other1");
            return (Criteria) this;
        }

        public Criteria andOther1Between(Integer value1, Integer value2) {
            addCriterion("other1 between", value1, value2, "other1");
            return (Criteria) this;
        }

        public Criteria andOther1NotBetween(Integer value1, Integer value2) {
            addCriterion("other1 not between", value1, value2, "other1");
            return (Criteria) this;
        }

        public Criteria andOther2IsNull() {
            addCriterion("other2 is null");
            return (Criteria) this;
        }

        public Criteria andOther2IsNotNull() {
            addCriterion("other2 is not null");
            return (Criteria) this;
        }

        public Criteria andOther2EqualTo(Integer value) {
            addCriterion("other2 =", value, "other2");
            return (Criteria) this;
        }

        public Criteria andOther2NotEqualTo(Integer value) {
            addCriterion("other2 <>", value, "other2");
            return (Criteria) this;
        }

        public Criteria andOther2GreaterThan(Integer value) {
            addCriterion("other2 >", value, "other2");
            return (Criteria) this;
        }

        public Criteria andOther2GreaterThanOrEqualTo(Integer value) {
            addCriterion("other2 >=", value, "other2");
            return (Criteria) this;
        }

        public Criteria andOther2LessThan(Integer value) {
            addCriterion("other2 <", value, "other2");
            return (Criteria) this;
        }

        public Criteria andOther2LessThanOrEqualTo(Integer value) {
            addCriterion("other2 <=", value, "other2");
            return (Criteria) this;
        }

        public Criteria andOther2In(List<Integer> values) {
            addCriterion("other2 in", values, "other2");
            return (Criteria) this;
        }

        public Criteria andOther2NotIn(List<Integer> values) {
            addCriterion("other2 not in", values, "other2");
            return (Criteria) this;
        }

        public Criteria andOther2Between(Integer value1, Integer value2) {
            addCriterion("other2 between", value1, value2, "other2");
            return (Criteria) this;
        }

        public Criteria andOther2NotBetween(Integer value1, Integer value2) {
            addCriterion("other2 not between", value1, value2, "other2");
            return (Criteria) this;
        }

        public Criteria andOther3IsNull() {
            addCriterion("other3 is null");
            return (Criteria) this;
        }

        public Criteria andOther3IsNotNull() {
            addCriterion("other3 is not null");
            return (Criteria) this;
        }

        public Criteria andOther3EqualTo(String value) {
            addCriterion("other3 =", value, "other3");
            return (Criteria) this;
        }

        public Criteria andOther3NotEqualTo(String value) {
            addCriterion("other3 <>", value, "other3");
            return (Criteria) this;
        }

        public Criteria andOther3GreaterThan(String value) {
            addCriterion("other3 >", value, "other3");
            return (Criteria) this;
        }

        public Criteria andOther3GreaterThanOrEqualTo(String value) {
            addCriterion("other3 >=", value, "other3");
            return (Criteria) this;
        }

        public Criteria andOther3LessThan(String value) {
            addCriterion("other3 <", value, "other3");
            return (Criteria) this;
        }

        public Criteria andOther3LessThanOrEqualTo(String value) {
            addCriterion("other3 <=", value, "other3");
            return (Criteria) this;
        }

        public Criteria andOther3Like(String value) {
            addCriterion("other3 like", value, "other3");
            return (Criteria) this;
        }

        public Criteria andOther3NotLike(String value) {
            addCriterion("other3 not like", value, "other3");
            return (Criteria) this;
        }

        public Criteria andOther3In(List<String> values) {
            addCriterion("other3 in", values, "other3");
            return (Criteria) this;
        }

        public Criteria andOther3NotIn(List<String> values) {
            addCriterion("other3 not in", values, "other3");
            return (Criteria) this;
        }

        public Criteria andOther3Between(String value1, String value2) {
            addCriterion("other3 between", value1, value2, "other3");
            return (Criteria) this;
        }

        public Criteria andOther3NotBetween(String value1, String value2) {
            addCriterion("other3 not between", value1, value2, "other3");
            return (Criteria) this;
        }

        public Criteria andOther4IsNull() {
            addCriterion("other4 is null");
            return (Criteria) this;
        }

        public Criteria andOther4IsNotNull() {
            addCriterion("other4 is not null");
            return (Criteria) this;
        }

        public Criteria andOther4EqualTo(String value) {
            addCriterion("other4 =", value, "other4");
            return (Criteria) this;
        }

        public Criteria andOther4NotEqualTo(String value) {
            addCriterion("other4 <>", value, "other4");
            return (Criteria) this;
        }

        public Criteria andOther4GreaterThan(String value) {
            addCriterion("other4 >", value, "other4");
            return (Criteria) this;
        }

        public Criteria andOther4GreaterThanOrEqualTo(String value) {
            addCriterion("other4 >=", value, "other4");
            return (Criteria) this;
        }

        public Criteria andOther4LessThan(String value) {
            addCriterion("other4 <", value, "other4");
            return (Criteria) this;
        }

        public Criteria andOther4LessThanOrEqualTo(String value) {
            addCriterion("other4 <=", value, "other4");
            return (Criteria) this;
        }

        public Criteria andOther4Like(String value) {
            addCriterion("other4 like", value, "other4");
            return (Criteria) this;
        }

        public Criteria andOther4NotLike(String value) {
            addCriterion("other4 not like", value, "other4");
            return (Criteria) this;
        }

        public Criteria andOther4In(List<String> values) {
            addCriterion("other4 in", values, "other4");
            return (Criteria) this;
        }

        public Criteria andOther4NotIn(List<String> values) {
            addCriterion("other4 not in", values, "other4");
            return (Criteria) this;
        }

        public Criteria andOther4Between(String value1, String value2) {
            addCriterion("other4 between", value1, value2, "other4");
            return (Criteria) this;
        }

        public Criteria andOther4NotBetween(String value1, String value2) {
            addCriterion("other4 not between", value1, value2, "other4");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}