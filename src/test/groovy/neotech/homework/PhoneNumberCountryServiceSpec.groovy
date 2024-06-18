package neotech.homework

import neotech.homework.exception.ApiException
import neotech.homework.service.PhoneNumberCountryService
import neotech.homework.utils.PhoneNumberUtils
import org.springframework.beans.factory.annotation.Autowired

class PhoneNumberCountryServiceSpec extends DatabaseSpecTemplate {

    @Autowired
    PhoneNumberCountryService phoneNumberCountryService

    def 'test getPhoneNumberCountryData() - should correctly find phone number country'() {
        expect: 'full integration test, when data was parsed from file and loaded to database'
        phoneNumberCountryService.getPhoneNumberCountryData(phoneNumber).countryNames() == countryData

        where:
        phoneNumber          | countryData
        '+12423222931'       | ['Bahamas']
        '+11165384765'       | ['Canada', 'United States']
        '+71423423412'       | ['Russia']
        '+77112227231'       | ['Kazakhstan']
        '+1-418-543-8090'    | ['Canada', 'United States']
        '+1-587-530-2271'    | ['Canada', 'United States']
        '+1-404-724-1937'    | ['Canada', 'United States']
        '+1-443-307-1473'    | ['Canada', 'United States']
        '+1-329-420-1792'    | ['Canada', 'United States']
        '+1-770-212-6011'    | ['Canada', 'United States']
        '+1-473-522-7496'    | ['Grenada']
        '+987-123-45670'     | ['Iran']
        '+123 456 78901'     | ['Canada', 'United States']

        // test data taken from: https://www.akto.io/tools/phone-number-generator
        '+49 8605 53405570'  | ['Germany']
        '+49 9496 45360396'  | ['Germany']
        '+49 7918 61580986'  | ['Germany']
        '+49 1393 89017875'  | ['Germany']
        '+44 05788 869281'   | ['United Kingdom']
        '+44 08680 082367'   | ['United Kingdom']
        '+44 07213 099362'   | ['United Kingdom']
        '+44 07692 869442'   | ['United Kingdom']
        '+81 12 6068 8474'   | ['Japan']
        '+81 08 9356 5576'   | ['Japan']
        '+81 44 7416 9832'   | ['Japan']
        '+81 50 8556 4422'   | ['Japan']
        '+91 1588922657'     | ['India']
        '+91 5476736968'     | ['India']
        '+91 9171941489'     | ['India']
        '+91 1816457203'     | ['India']
        '+86 92 2932 5314'   | ['China']
        '+86 80 9227 6917'   | ['China']
        '+86 44 9021 5393'   | ['China']
        '+86 37 9816 0926'   | ['China']
        '+90 (807) 566-2706' | ['Turkey']
        '+90 (588) 159-1289' | ['Turkey']
        '+90 (566) 832-5160' | ['Turkey']
        '+90 (642) 947-5335' | ['Turkey']
        '+61 04 2835 1305'   | ['Australia']
        '+61 09 8870 6458'   | ['Australia']
        '+61 06 5264 8806'   | ['Australia']
        '+61 01 5469 0640'   | ['Australia']
        '+33 7144869947'     | ['France']
        '+33 3964437381'     | ['France']
        '+33 2458460636'     | ['France']
        '+33 0714960511'     | ['France']
        '+55 (97) 8889-8784' | ['Brazil']
        '+55 (84) 3097-0473' | ['Brazil']
        '+55 (60) 6953-1431' | ['Brazil']
        '+55 (49) 4796-7001' | ['Brazil']
        '+7 (591) 633-78-47' | ['Russia']
        '+7 (352) 003-68-42' | ['Russia']
        '+7 (859) 287-04-56' | ['Russia']
        '+7 (855) 773-45-43' | ['Russia']
        '+27 49 504 5893'    | ['South Africa']
        '+27 41 561 1673'    | ['South Africa']
        '+27 56 571 9819'    | ['South Africa']
        '+27 36 472 9472'    | ['South Africa']
        '+52 50 8312 8113'   | ['Mexico']
        '+52 20 8176 3961'   | ['Mexico']
        '+52 51 0616 0901'   | ['Mexico']
        '+52 89 7524 7159'   | ['Mexico']
        '+234 685 6068240'   | ['Nigeria']
        '+234 449 9765015'   | ['Nigeria']
        '+234 759 0394846'   | ['Nigeria']
        '+234 117 3829259'   | ['Nigeria']
        '+966 2 3010 7113'   | ['Saudi Arabia']
        '+966 0 1998 2967'   | ['Saudi Arabia']
        '+966 0 0216 8345'   | ['Saudi Arabia']
        '+966 4 3360 8973'   | ['Saudi Arabia']
        '+82 0 735 3429'     | ['Korea, South']
        '+82 8 351 8528'     | ['Korea, South']
        '+82 0 173 4514'     | ['Korea, South']
        '+82 3 492 8686'     | ['Korea, South']
        '+39 177 5871840'    | ['Italy']
        '+39 327 8387175'    | ['Italy']
        '+39 532 3281765'    | ['Italy']
        '+39 379 8773671'    | ['Vatican City State (Holy See)']
        '+34 682199414'      | ['Spain']
        '+34 815820452'      | ['Spain']
        '+34 945338895'      | ['Spain']
        '+34 446494409'      | ['Spain']
        '+92 343 8574939'    | ['Pakistan']
        '+92 531 5809776'    | ['Pakistan']
        '+92 627 8622390'    | ['Pakistan']
        '+92 772 9019205'    | ['Pakistan']
        '+880 82 4290267'    | ['Bangladesh']
        '+880 16 1859146'    | ['Bangladesh']
        '+880 30 4090735'    | ['Bangladesh']
        '+880 08 0476735'    | ['Bangladesh']
        '+84 324 732 0869'   | ['Vietnam']
        '+84 726 229 5639'   | ['Vietnam']
        '+84 131 934 4639'   | ['Vietnam']
        '+84 666 705 9042'   | ['Vietnam']
        '+63 9990 1197869'   | ['Philippines']
        '+63 7932 2640200'   | ['Philippines']
        '+63 3628 2516645'   | ['Philippines']
        '+63 9584 1628150'   | ['Philippines']
        '+20 58 844 1551'    | ['Egypt']
        '+20 96 315 9137'    | ['Egypt']
        '+20 88 751 3008'    | ['Egypt']
        '+20 73 258 3911'    | ['Egypt']
        '+66 (12) 1725 4000' | ['Thailand']
        '+66 (40) 3912 4525' | ['Thailand']
        '+66 (92) 7151 3509' | ['Thailand']
        '+66 (81) 9714 4382' | ['Thailand']
        '+54 85 4105-0837'   | ['Argentina']
        '+54 98 4791-8972'   | ['Argentina']
        '+54 33 6305-8509'   | ['Argentina']
        '+54 22 7014-4095'   | ['Argentina']
        '+57 (853) 944-3470' | ['Colombia']
        '+57 (212) 148-9754' | ['Colombia']
        '+57 (928) 586-7270' | ['Colombia']
        '+57 (767) 675-3755' | ['Colombia']
        '+48 865 011 465'    | ['Poland']
        '+48 913 794 823'    | ['Poland']
        '+48 463 304 660'    | ['Poland']
        '+48 691 568 458'    | ['Poland']
        '+98 897 847 4351'   | ['Iran']
        '+98 267 987 7491'   | ['Iran']
        '+98 765 196 4363'   | ['Iran']
        '+98 587 920 5559'   | ['Iran']
        '+60 97 8454 0017'   | ['Malaysia']
        '+60 64 3926 1959'   | ['Malaysia']
        '+60 51 6657 8706'   | ['Malaysia']
        '+60 72 8904 0401'   | ['Malaysia']
        '+380 (74) 644-3125' | ['Ukraine']
        '+380 (06) 800-6672' | ['Ukraine']
        '+380 (51) 164-3325' | ['Ukraine']
        '+380 (75) 118-5872' | ['Ukraine']
        '+254 360 4122516'   | ['Kenya']
        '+254 768 5059114'   | ['Kenya']
        '+254 324 1718767'   | ['Kenya']
        '+254 201 1998943'   | ['Kenya']
        '+30 667 8955872'    | ['Greece']
        '+30 004 9503591'    | ['Greece']
        '+30 340 5858775'    | ['Greece']
        '+30 846 7830728'    | ['Greece']
        '+46 69 177 05 26'   | ['Sweden']
        '+46 92 661 05 89'   | ['Sweden']
        '+46 03 179 61 78'   | ['Sweden']
        '+46 12 250 27 68'   | ['Sweden']
        // TODO: Implement all test cases

    }

    def 'test getPhoneNumberCountryData() - should throw error for invalid phone number'() {
        when:
        phoneNumberCountryService.getPhoneNumberCountryData(invalidPhoneNumber)

        then:
        def error = thrown(ApiException)
        error.message == expectedMessage

        where:
        invalidPhoneNumber                                               | expectedMessage
        null                                                             | 'The phone number supplied was empty'
        ''                                                               | 'The phone number supplied was empty'
        '      '                                                         | 'The phone number supplied was empty'
        '+1' + '8'.repeat(PhoneNumberUtils.MAX_INPUT_STRING_LENGTH + 1)  | 'The phone number string supplied was too long to parse'
        '12423222931'                                                    | "${invalidPhoneNumber} phone should begin with '+' sign"
        '46 12 250 27 68'                                                | "${invalidPhoneNumber} phone should begin with '+' sign"
        '+1242a222931'                                                   | "Invalid phone number format: ${invalidPhoneNumber}. Legal phone should look like +371 1123, +1 (555) 111 4555, +15555"
        '+1242!222931'                                                   | "Invalid phone number format: ${invalidPhoneNumber}. Legal phone should look like +371 1123, +1 (555) 111 4555, +15555"
        '+12 [424] 222931'                                               | "Invalid phone number format: ${invalidPhoneNumber}. Legal phone should look like +371 1123, +1 (555) 111 4555, +15555"
        '+' + '1'.repeat(PhoneNumberUtils.LEGAL_PHONE_NUMBER_LENGTH - 1) | "Invalid phone number length. Legal number must have not less than ${PhoneNumberUtils.LEGAL_PHONE_NUMBER_LENGTH} digits"
    }
}
