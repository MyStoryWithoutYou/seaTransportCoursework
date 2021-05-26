package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.OrderInfo;
import com.mycompany.myapp.repository.OrderInfoRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link OrderInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OrderInfoResourceIT {

    private static final Integer DEFAULT_ORDER = 0;
    private static final Integer UPDATED_ORDER = 1;

    private static final Integer DEFAULT_LENGTH = 0;
    private static final Integer UPDATED_LENGTH = 1;

    private static final Integer DEFAULT_WIDTH = 0;
    private static final Integer UPDATED_WIDTH = 1;

    private static final Integer DEFAULT_HEIGHT = 0;
    private static final Integer UPDATED_HEIGHT = 1;

    private static final Integer DEFAULT_VOLUME = 0;
    private static final Integer UPDATED_VOLUME = 1;

    private static final Integer DEFAULT_WEIGHT = 0;
    private static final Integer UPDATED_WEIGHT = 1;

    private static final String ENTITY_API_URL = "/api/order-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderInfoMockMvc;

    private OrderInfo orderInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderInfo createEntity(EntityManager em) {
        OrderInfo orderInfo = new OrderInfo()
            .order(DEFAULT_ORDER)
            .length(DEFAULT_LENGTH)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT)
            .volume(DEFAULT_VOLUME)
            .weight(DEFAULT_WEIGHT);
        return orderInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderInfo createUpdatedEntity(EntityManager em) {
        OrderInfo orderInfo = new OrderInfo()
            .order(UPDATED_ORDER)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .volume(UPDATED_VOLUME)
            .weight(UPDATED_WEIGHT);
        return orderInfo;
    }

    @BeforeEach
    public void initTest() {
        orderInfo = createEntity(em);
    }

    @Test
    @Transactional
    void createOrderInfo() throws Exception {
        int databaseSizeBeforeCreate = orderInfoRepository.findAll().size();
        // Create the OrderInfo
        restOrderInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderInfo)))
            .andExpect(status().isCreated());

        // Validate the OrderInfo in the database
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeCreate + 1);
        OrderInfo testOrderInfo = orderInfoList.get(orderInfoList.size() - 1);
        assertThat(testOrderInfo.getOrder()).isEqualTo(DEFAULT_ORDER);
        assertThat(testOrderInfo.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testOrderInfo.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testOrderInfo.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testOrderInfo.getVolume()).isEqualTo(DEFAULT_VOLUME);
        assertThat(testOrderInfo.getWeight()).isEqualTo(DEFAULT_WEIGHT);
    }

    @Test
    @Transactional
    void createOrderInfoWithExistingId() throws Exception {
        // Create the OrderInfo with an existing ID
        orderInfo.setId(1L);

        int databaseSizeBeforeCreate = orderInfoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderInfo)))
            .andExpect(status().isBadRequest());

        // Validate the OrderInfo in the database
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkOrderIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderInfoRepository.findAll().size();
        // set the field null
        orderInfo.setOrder(null);

        // Create the OrderInfo, which fails.

        restOrderInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderInfo)))
            .andExpect(status().isBadRequest());

        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLengthIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderInfoRepository.findAll().size();
        // set the field null
        orderInfo.setLength(null);

        // Create the OrderInfo, which fails.

        restOrderInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderInfo)))
            .andExpect(status().isBadRequest());

        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkWidthIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderInfoRepository.findAll().size();
        // set the field null
        orderInfo.setWidth(null);

        // Create the OrderInfo, which fails.

        restOrderInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderInfo)))
            .andExpect(status().isBadRequest());

        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkHeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderInfoRepository.findAll().size();
        // set the field null
        orderInfo.setHeight(null);

        // Create the OrderInfo, which fails.

        restOrderInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderInfo)))
            .andExpect(status().isBadRequest());

        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkVolumeIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderInfoRepository.findAll().size();
        // set the field null
        orderInfo.setVolume(null);

        // Create the OrderInfo, which fails.

        restOrderInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderInfo)))
            .andExpect(status().isBadRequest());

        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderInfoRepository.findAll().size();
        // set the field null
        orderInfo.setWeight(null);

        // Create the OrderInfo, which fails.

        restOrderInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderInfo)))
            .andExpect(status().isBadRequest());

        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllOrderInfos() throws Exception {
        // Initialize the database
        orderInfoRepository.saveAndFlush(orderInfo);

        // Get all the orderInfoList
        restOrderInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].order").value(hasItem(DEFAULT_ORDER)))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].volume").value(hasItem(DEFAULT_VOLUME)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)));
    }

    @Test
    @Transactional
    void getOrderInfo() throws Exception {
        // Initialize the database
        orderInfoRepository.saveAndFlush(orderInfo);

        // Get the orderInfo
        restOrderInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, orderInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderInfo.getId().intValue()))
            .andExpect(jsonPath("$.order").value(DEFAULT_ORDER))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT))
            .andExpect(jsonPath("$.volume").value(DEFAULT_VOLUME))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT));
    }

    @Test
    @Transactional
    void getNonExistingOrderInfo() throws Exception {
        // Get the orderInfo
        restOrderInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewOrderInfo() throws Exception {
        // Initialize the database
        orderInfoRepository.saveAndFlush(orderInfo);

        int databaseSizeBeforeUpdate = orderInfoRepository.findAll().size();

        // Update the orderInfo
        OrderInfo updatedOrderInfo = orderInfoRepository.findById(orderInfo.getId()).get();
        // Disconnect from session so that the updates on updatedOrderInfo are not directly saved in db
        em.detach(updatedOrderInfo);
        updatedOrderInfo
            .order(UPDATED_ORDER)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .volume(UPDATED_VOLUME)
            .weight(UPDATED_WEIGHT);

        restOrderInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOrderInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOrderInfo))
            )
            .andExpect(status().isOk());

        // Validate the OrderInfo in the database
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeUpdate);
        OrderInfo testOrderInfo = orderInfoList.get(orderInfoList.size() - 1);
        assertThat(testOrderInfo.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testOrderInfo.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testOrderInfo.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testOrderInfo.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testOrderInfo.getVolume()).isEqualTo(UPDATED_VOLUME);
        assertThat(testOrderInfo.getWeight()).isEqualTo(UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    void putNonExistingOrderInfo() throws Exception {
        int databaseSizeBeforeUpdate = orderInfoRepository.findAll().size();
        orderInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orderInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderInfo in the database
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOrderInfo() throws Exception {
        int databaseSizeBeforeUpdate = orderInfoRepository.findAll().size();
        orderInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orderInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderInfo in the database
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOrderInfo() throws Exception {
        int databaseSizeBeforeUpdate = orderInfoRepository.findAll().size();
        orderInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderInfo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderInfo in the database
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOrderInfoWithPatch() throws Exception {
        // Initialize the database
        orderInfoRepository.saveAndFlush(orderInfo);

        int databaseSizeBeforeUpdate = orderInfoRepository.findAll().size();

        // Update the orderInfo using partial update
        OrderInfo partialUpdatedOrderInfo = new OrderInfo();
        partialUpdatedOrderInfo.setId(orderInfo.getId());

        partialUpdatedOrderInfo.height(UPDATED_HEIGHT).volume(UPDATED_VOLUME).weight(UPDATED_WEIGHT);

        restOrderInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrderInfo))
            )
            .andExpect(status().isOk());

        // Validate the OrderInfo in the database
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeUpdate);
        OrderInfo testOrderInfo = orderInfoList.get(orderInfoList.size() - 1);
        assertThat(testOrderInfo.getOrder()).isEqualTo(DEFAULT_ORDER);
        assertThat(testOrderInfo.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testOrderInfo.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testOrderInfo.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testOrderInfo.getVolume()).isEqualTo(UPDATED_VOLUME);
        assertThat(testOrderInfo.getWeight()).isEqualTo(UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    void fullUpdateOrderInfoWithPatch() throws Exception {
        // Initialize the database
        orderInfoRepository.saveAndFlush(orderInfo);

        int databaseSizeBeforeUpdate = orderInfoRepository.findAll().size();

        // Update the orderInfo using partial update
        OrderInfo partialUpdatedOrderInfo = new OrderInfo();
        partialUpdatedOrderInfo.setId(orderInfo.getId());

        partialUpdatedOrderInfo
            .order(UPDATED_ORDER)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .volume(UPDATED_VOLUME)
            .weight(UPDATED_WEIGHT);

        restOrderInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrderInfo))
            )
            .andExpect(status().isOk());

        // Validate the OrderInfo in the database
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeUpdate);
        OrderInfo testOrderInfo = orderInfoList.get(orderInfoList.size() - 1);
        assertThat(testOrderInfo.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testOrderInfo.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testOrderInfo.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testOrderInfo.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testOrderInfo.getVolume()).isEqualTo(UPDATED_VOLUME);
        assertThat(testOrderInfo.getWeight()).isEqualTo(UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    void patchNonExistingOrderInfo() throws Exception {
        int databaseSizeBeforeUpdate = orderInfoRepository.findAll().size();
        orderInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, orderInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(orderInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderInfo in the database
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOrderInfo() throws Exception {
        int databaseSizeBeforeUpdate = orderInfoRepository.findAll().size();
        orderInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(orderInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderInfo in the database
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOrderInfo() throws Exception {
        int databaseSizeBeforeUpdate = orderInfoRepository.findAll().size();
        orderInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderInfoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(orderInfo))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderInfo in the database
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOrderInfo() throws Exception {
        // Initialize the database
        orderInfoRepository.saveAndFlush(orderInfo);

        int databaseSizeBeforeDelete = orderInfoRepository.findAll().size();

        // Delete the orderInfo
        restOrderInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, orderInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        assertThat(orderInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
